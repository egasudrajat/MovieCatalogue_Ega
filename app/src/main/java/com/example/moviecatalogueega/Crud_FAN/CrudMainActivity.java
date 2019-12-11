package com.example.moviecatalogueega.Crud_FAN;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.moviecatalogueega.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CrudMainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNama, etKelas, etAlamat;
    private Button btntmbah, btnlihat, btbrowse, btupload;
    private ImageView imgbrowse;
    private static final String TAG = "CrudMainActivity";
    Bitmap bitmap, decoded;
    int bitmap_size = 60;

    private Uri filePath;

    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud_activity);
        etNama = findViewById(R.id.editTextMainnama);
        etKelas = findViewById(R.id.editTextMainkelas);
        etAlamat = findViewById(R.id.editTextMainalamat);
        btbrowse = findViewById(R.id.btn_browse);
        btupload = findViewById(R.id.btn_upload);
        imgbrowse = findViewById(R.id.imagebrowse);
        btntmbah = findViewById(R.id.buttonMainTambah);
        btnlihat = findViewById(R.id.buttonMainLihat);
        btntmbah.setOnClickListener(this);
        btnlihat.setOnClickListener(this);
        btbrowse.setOnClickListener(this);
        btupload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonMainTambah) {
            String nama = etNama.getText().toString(); //mengambil Value etNim menjadi string
            String kelas = etKelas.getText().toString(); //mengambil Value etNim menjadi string
            String alamat = etAlamat.getText().toString();

            if (nama.equals("") || kelas.equals("") || alamat.equals("")) {
                Toast.makeText(this, "Semua data harus diisi", Toast.LENGTH_SHORT).show();
                //memunculkan toast saat form masih ada yang kosong
            } else {
                tambahData(nama, kelas, alamat); //memanggil fungsi tambahData()

//                etNama.setText(""); //mengosongkan form setelah data berhasil ditambahkan
//                etKelas.setText(""); //mengosongkan form setelah data berhasil ditambahkan
//                etAlamat.setText(""); //mengosongkan form setelah data berhasil ditambahkan
            }
        } else if (v.getId() == R.id.buttonMainLihat) {
            Intent intent = new Intent(this, ActivityLihatData.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_browse) {

        //===============Browse gampar dri galeri =======
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select picture"), PICK_IMAGE);
        } else if (v.getId() == R.id.btn_upload) {
            UploadImage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            try {
                //mengambil fambar dari Gallery
                filePath = data.getData();
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), filePath);
                bitmap = ImageDecoder.decodeBitmap(source);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView(getResizedBitmap(bitmap, 512));
                etAlamat.setText(String.valueOf(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    //============= proses set bitmap gambar ke imageview=========
    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imgbrowse.setImageBitmap(decoded);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
//====================== end bitmap =======================

    public void tambahData(String nama, String kelas, String alamat) {
        //koneksi ke file create.php, jika menggunakan localhost gunakan ip sesuai dengan ip kamu
        AndroidNetworking.post("http://192.168.43.30/api_siswa/insert.php")
                .addBodyParameter("nama", nama) //mengirimkan data nim_mahasiswa yang akan diisi dengan varibel nim
                .addBodyParameter("kelas", kelas) //mengirimkan data nama_mahasiswa yang akan diisi dengan varibel nama
                .addBodyParameter("alamat", alamat) //mengirimkan data kelas_mahasiswa yang akan diisi dengan varibel kelas
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
                        Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data berhasil ditambahkan
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, "onError: Failed" + anError); //untuk log pada onerror
                        Toast.makeText(getApplicationContext(), "Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data gagal ditambahkan
                    }
                });
    }

    public void UploadImage() {
        String gbr = getStringImage(decoded);
        AndroidNetworking.post("http://192.168.43.30/upload/upload.php")
                .addBodyParameter("image",gbr) //mengirimkan data kelas_mahasiswa yang akan diisi dengan varibel kelas
                .addBodyParameter("name", "sasasa") //mengirimkan data nama_mahasiswa yang akan diisi dengan varibel nama
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, "Response: " + response);

                        try {

                            int success = response.getInt("success");

                            if (success == 1) {
                                Log.e("v Add", response.toString());

                                Toast.makeText(CrudMainActivity.this, response.getString("message"), Toast.LENGTH_LONG).show();


                            } else {
                                Toast.makeText(CrudMainActivity.this, response.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }}


                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(CrudMainActivity.this, "Error cuy", Toast.LENGTH_LONG).show();

                    }
                });
    }
}
