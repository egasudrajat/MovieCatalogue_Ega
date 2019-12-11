package com.example.moviecatalogueega;

import com.example.moviecatalogueega.Model.ModelFilm;

import java.util.ArrayList;

public class DataFilm {
    private static String[] namafilm = {"Venom",
            "Aquaman",
            "Avengers Infinity War",
            "Bird box",
            "Bohemian Rhapsody",
            "Bumble Bee",
            "Creed II",
            "Once Upon a Dead Pool",
            "Mortal Engines",
            "Preman Pensiun",
            "Spiderman: Into the Spider Verse",
            "The Mule"

    };

    private static String[] deskripsifilm = {"Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote. Soon, he must rely on his newfound powers to protect the world from a shadowy organization looking for a symbiote of their own.",
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
            "Five years after an ominous unseen presence drives most of society to suicide, a survivor and her two children make a desperate bid to reach safety.",
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            "On the run in the year 1987, Bumblebee finds refuge in a junkyard in a small Californian beach town. Charlie, on the cusp of turning 18 and trying to find her place in the world, discovers Bumblebee, battle-scarred and broken. When Charlie revives him, she quickly learns this is no ordinary yellow VW bug.",
            "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
            "A kidnapped Fred Savage is forced to endure Deadpool's PG-13 rendition of Deadpool 2 as a Princess Bride-esque story that's full of magic, wonder & zero F's.",
            "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
            "After three years, the business of Muslihat (Epi Kusnandar), who has retired as a thug, has a problem. Sales decline. Muslihat also faces new problems when Safira (Safira Maharani), her only daughter, grows up in adolescence and begins to be visited by boys. A bigger problem: frictions between his former subordinates.",
            "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            "Earl Stone, a man in his 80s, is broke, alone, and facing foreclosure of his business when he is offered a job that simply requires him to drive. Easy enough, but, unbeknownst to Earl, he’s just signed on as a drug courier for a Mexican cartel. He does so well that his cargo increases exponentially, and Earl hit the radar of hard-charging DEA agent Colin Bates."

    };

    private static int[] posterfilm = {R.drawable.poster_venom,
            R.drawable.poster_aquaman,
            R.drawable.poster_avengerinfinity,
            R.drawable.poster_birdbox,
            R.drawable.poster_bohemian,
            R.drawable.poster_bumblebee,
            R.drawable.poster_creed,
            R.drawable.poster_deadpool,
            R.drawable.poster_mortalengine,
            R.drawable.poster_preman,
            R.drawable.poster_spiderman,
            R.drawable.poster_themule

    };

    private static String[] tanggalrilisfilm = {
            " September 28, 2018",
            "December 7, 2018",
            "April 25, 2018",
            "December 13, 2018",
            "October 24, 2018",
            "December 15, 2018",
            "November 21, 2018",
            "December 11, 2018",
            "November 27, 2018",
            "January 17, 2019",
            "December 6, 2018",
            "December 14, 2018"


    };

    private static String[] durasifilm = {"1h 52m",
            "2h 24m",
            "2h 29m",
            "2h 4m",
            "2h 15m",
            "1h 54m",
            "2h 10m",
            "1h 57m",
            "2h 9m",
            "1h 34m",
            "1h 57m",
            "1h 57m"

    };


    public static ArrayList<ModelFilm> GetListData() {
        ArrayList<ModelFilm> List = new ArrayList<>();
        for (int position = 0; position < namafilm.length; position++) {
            ModelFilm modelFilm = new ModelFilm();
            modelFilm.setNama(namafilm[position]);
            modelFilm.setDeskripsi(deskripsifilm[position]);
            modelFilm.setTanggal(tanggalrilisfilm[position]);
            modelFilm.setPoster(posterfilm[position]);
            modelFilm.setDurasi(durasifilm[position]);
            List.add(modelFilm);
        }
        return List;
    }

}

