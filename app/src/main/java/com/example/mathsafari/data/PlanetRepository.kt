package com.example.mathsafari.data

object PlanetRepository {
    val planets = listOf(
        Planet(
            id = "sun", name = "Słońce", number = "Gwiazda Centralna", emoji = "☀️",
            desc = "Słońce to ogromna gwiazda w centrum naszego układu. To ona daje nam światło i ciepło, dzięki którym możemy żyć!",
            facts = listOf(
                "🔥 Temperatura powierzchni to około 5 500 °C!",
                "⚖️ Masa Słońca to 99,8% masy całego Układu Słonecznego.",
                "🌍 Wewnątrz Słońca zmieściłoby się ponad milion Ziem!",
                "⏳ Słońce ma około 4,6 miliarda lat."
            ),
            wow = "Światło ze Słońca potrzebuje aż 8 minut, żeby dotrzeć do Ziemi!"
        ),
        Planet(
            id = "mercury", name = "Merkury", number = "1. planeta od Słońca", emoji = "🪨",
            desc = "Merkury to najmniejsza planeta i najbliżej Słońca. Mimo bycia blisko Słońca, w nocy jest tam strasznie zimno!",
            facts = listOf(
                "🌡️ Temperatura waha się od –180 °C (noc) do +430 °C (dzień)!",
                "📅 Rok trwa tylko 88 ziemskich dni.",
                "🌙 Merkury jest tylko trochę większy od Księżyca.",
                "💨 Prawie nie ma atmosfery – bez ochrony przed przestrzenią kosmiczną."
            ),
            wow = "Na Merkurym dzień trwa DŁUŻEJ niż rok! Jeden obrót = 59 dni Ziemi, ale rok = 88 dni."
        ),
        Planet(
            id = "venus", name = "Wenus", number = "2. planeta od Słońca", emoji = "☁️",
            desc = "Wenus jest prawie tak duża jak Ziemia, ale jest najgorętszą planetą w Układzie Słonecznym! Gęste chmury zatrzymują ciepło jak koc.",
            facts = listOf(
                "🔥 Temperatura powierzchni: aż 465 °C – gorąco jak piekarnik × 5!",
                "🔄 Obraca się w przeciwnym kierunku niż większość planet.",
                "☁️ Chmury zawierają kwas siarkowy.",
                "🌟 Najjaśniejszy obiekt nocnego nieba (po Księżycu)."
            ),
            wow = "Na Wenus dzień trwa dłużej niż rok! Powoli się kręci, ale szybko obiega Słońce."
        ),
        Planet(
            id = "earth", name = "Ziemia", number = "3. planeta od Słońca", emoji = "🌍",
            desc = "Nasz dom! Ziemia jest trzecią planetą od Słońca i jedyną, na której żyją ludzie, zwierzęta i rośliny.",
            facts = listOf(
                "💧 Ponad 70 % powierzchni to oceany!",
                "🌙 Ma jeden naturalny księżyc – Księżyc.",
                "🌡️ Idealna temperatura dla życia dzięki atmosferze.",
                "🛡️ Pole magnetyczne chroni nas przed promieniowaniem kosmicznym."
            ),
            wow = "Ziemia jest jedynym miejscem we wszechświecie, gdzie potwierdzono istnienie życia!"
        ),
        Planet(
            id = "moon", name = "Księżyc", number = "Satelita Ziemi", emoji = "🌙",
            desc = "Księżyc to nasz jedyny naturalny satelita. Choć świeci w nocy, nie ma własnego światła – odbija tylko światło słoneczne!",
            facts = listOf(
                "👣 Pierwszy człowiek stanął na Księżycu w 1969 roku.",
                "🌑 Księżyc powoduje przypływy i odpływy oceanów na Ziemi.",
                "🧊 Na biegunach Księżyca może znajdować się zamrożona woda.",
                "🌬️ Na Księżycu nie ma atmosfery ani wiatru – ślady astronautów zostaną tam na miliony lat!"
            ),
            wow = "Na Księżycu ważyłbyś 6 razy mniej niż na Ziemi! Mógłbyś skakać bardzo wysoko."
        ),
        Planet(
            id = "mars", name = "Mars", number = "4. planeta od Słońca", emoji = "🔴",
            desc = "Mars to Czerwona Planeta! Jest zimniejszy i mniejszy od Ziemi. Naukowcy wysyłają tam łaziki, żeby go zbadać.",
            facts = listOf(
                "🔴 Czerwony kolor pochodzi od rdzy (tlenku żelaza) na powierzchni.",
                "⛰️ Olimp Mons – najwyższy wulkan w Układzie Słonecznym (27 km!).",
                "🌬️ Zdarzają się tam ogromne burze pyłowe trwające miesiącami.",
                "🤖 Na Marsie pracują łaziki: Curiosity i Perseverance!"
            ),
            wow = "Dzień na Marsie trwa prawie tyle samo co na Ziemi – 24 godziny i 37 minut!"
        ),
        Planet(
            id = "jupiter", name = "Jowisz", number = "5. planeta od Słońca", emoji = "🪐",
            desc = "Jowisz to gazowy olbrzym i największa planeta. Nie ma stałej powierzchni – zbudowany jest głównie z wodoru i helu.",
            facts = listOf(
                "🏆 Największa planeta – w środku zmieściłoby się 1 300 Ziemi!",
                "🌀 Wielka Czerwona Plama to burza trwająca ponad 400 lat.",
                "🛸 Ma co najmniej 95 księżyców!",
                "⚡ Wiatry osiągają 620 km/h."
            ),
            wow = "Jowisz działa jak „kosmiczny odkurzacz\" – swoją grawitacją przyciąga asteroidy i chroni Ziemię!"
        ),
        Planet(
            id = "saturn", name = "Saturn", number = "6. planeta od Słońca", emoji = "💍",
            desc = "Saturn to gazowy olbrzym ze wspaniałymi pierścieniami. Pierścienie zbudowane są z kawałków lodu i skał!",
            facts = listOf(
                "💍 Pierścienie rozciągają się na 280 000 km od planety!",
                "🪶 Jest tak lekki, że mógłby pływać na wodzie!",
                "🌙 Ma 146 znanych księżyców.",
                "💨 Wiatry osiągają 1 800 km/h!"
            ),
            wow = "Saturn jest jedyną planetą, która pływałaby na wodzie – jest mniej gęsty od H₂O!"
        ),
        Planet(
            id = "uranus", name = "Uran", number = "7. planeta od Słońca", emoji = "🔵",
            desc = "Uran to lodowy olbrzym. Jest przechylony tak bardzo, że prawie leży na boku – kręci się jak tocząca się piłka!",
            facts = listOf(
                "↔️ Obraca się niemal poziomo – oś przechylona o 98°!",
                "🥶 Najzimniejsza planeta – do –224 °C.",
                "💍 Ma 13 słabych pierścieni.",
                "📅 Rok trwa 84 lata ziemskie."
            ),
            wow = "Uran leży na boku! Prawdopodobnie dawno temu zderzyła się z nim ogromna asteroida."
        ),
        Planet(
            id = "neptune", name = "Neptun", number = "8. planeta od Słońca", emoji = "🌊",
            desc = "Neptun to ostatnia planeta w Układzie Słonecznym. Jest bardzo daleko od Słońca i niesamowicie wietrzno!",
            facts = listOf(
                "💨 Najsilniejsze wiatry w Układzie Słonecznym – 2 100 km/h!",
                "🔭 Odkryty dzięki matematyce, zanim ktoś go zobaczył przez teleskop!",
                "📅 Rok trwa 165 ziemskich lat.",
                "🌊 Nazwany na cześć rzymskiego boga morza."
            ),
            wow = "Neptun odkryto przez obliczenia matematyczne, zanim użyto teleskopu – nauka jest niesamowita!"
        )
    )

    val badges = listOf(
        Badge("first_quiz", "🎓", "Pierwszy Krok", "Ukończ swój pierwszy quiz."),
        Badge("perfect", "💯", "Perfekcja!", "Zdobądź 10/10 w quizie."),
        Badge("explorer", "🔭", "Wielki Odkrywca", "Kliknij każdą z 8 planet."),
        Badge("streak3", "🔥", "Płonący!", "3 poprawne odpowiedzi z rzędu."),
        Badge("streak5", "⚡", "Błyskawica!", "5 poprawnych odpowiedzi z rzędu."),
        Badge("quick", "🚀", "Rakieta!", "Odpowiedz w mniej niż 5 sekund."),
        Badge("pts100", "⭐", "Kosmonauta", "Zdobądź 100 punktów."),
        Badge("pts300", "🌟", "Odkrywca Planet", "Zdobądź 300 punktów."),
        Badge("pts500", "🏆", "Mistrz Kosmosu", "Zdobądź 500 punktów."),
        Badge("quiz3", "🎮", "Gracz", "Zagraj w quiz 3 razy.")
    )

    val levels = listOf(
        Level(0, "📚", "Uczeń"),
        Level(50, "👨\u200D🚀", "Astronauta"),
        Level(150, "🧑\u200D🚀", "Kosmonauta"),
        Level(300, "🔭", "Odkrywca Planet"),
        Level(500, "🏆", "Mistrz Kosmosu")
    )

    val questions = listOf(
        Question("Która planeta jest najbliżej Słońca?", listOf("Wenus", "Merkury", "Ziemia", "Mars"), 1, "Merkury jest pierwszą planetą od Słońca!"),
        Question("Która planeta jest największa w Układzie Słonecznym?", listOf("Saturn", "Neptun", "Uran", "Jowisz"), 3, "Jowisz mieści w sobie ponad 1 300 Ziemi!"),
        Question("Która planeta słynie z pięknych pierścieni?", listOf("Jowisz", "Saturn", "Uran", "Neptun"), 1, "Saturn ma najlepiej widoczne pierścienie z lodu i skał!"),
        Question("Ile planet ma Układ Słoneczny?", listOf("7", "9", "8", "10"), 2, "Od 2006 roku oficjalnie 8 planet!"),
        Question("Która planeta jest czerwona?", listOf("Mars", "Wenus", "Merkury", "Saturn"), 0, "Mars jest czerwony z powodu rdzy (tlenku żelaza)!"),
        Question("Na której planecie mieszkamy?", listOf("Mars", "Wenus", "Ziemia", "Merkury"), 2, "Żyjemy na Ziemi – 3. planecie od Słońca!"),
        Question("Która planeta jest ostatnia – najdalej od Słońca?", listOf("Uran", "Saturn", "Neptun", "Jowisz"), 2, "Neptun jest ósmą i ostatnią planetą!"),
        Question("Która planeta jest najgorętszą?", listOf("Merkury", "Wenus", "Mars", "Jowisz"), 1, "Wenus (465 °C) jest gorętsza niż Merkury dzięki efektowi cieplarnianemu!"),
        Question("Która planeta kręci się leżąc na boku?", listOf("Neptun", "Saturn", "Uran", "Jowisz"), 2, "Oś Urana jest przechylona o 98°!"),
        Question("Ile księżyców ma Ziemia?", listOf("2", "0", "3", "1"), 3, "Ziemia ma jeden księżyc – Księżyc!"),
        Question("Jak nazywa się największy wulkan w Układzie Słonecznym?", listOf("Olimp Mons", "Everest", "Wezuwiusz", "Etna"), 0, "Olimp Mons na Marsie ma 27 km wysokości!"),
        Question("Która planeta mogłaby pływać na wodzie?", listOf("Jowisz", "Uran", "Saturn", "Neptun"), 2, "Saturn jest mniej gęsty niż woda!"),
        Question("Jaki kolor ma planeta Uran?", listOf("Czerwony", "Fioletowy", "Błękitny", "Zielony"), 2, "Metan w atmosferze nadaje Uranowi błękitny kolor!"),
        Question("Która planeta ma Wielką Czerwoną Plamę?", listOf("Mars", "Saturn", "Neptun", "Jowisz"), 3, "To burza na Jowiszu trwająca ponad 400 lat!"),
        Question("Która planeta leży między Ziemią a Jowiszem?", listOf("Saturn", "Wenus", "Mars", "Merkury"), 2, "Mars (4.) jest między Ziemią (3.) a Jowiszem (5.)!"),
        Question("Ile czasu trwa jeden rok na Ziemi?", listOf("365 dni", "24 godziny", "200 dni", "12 dni"), 0, "365 dni – tyle Ziemia potrzebuje na okrążenie Słońca!"),
        Question("Która planeta jest najmniejsza?", listOf("Mars", "Merkury", "Wenus", "Neptun"), 1, "Merkury jest najmniejszą planetą Układu Słonecznego!"),
        Question("Co to jest Układ Słoneczny?", listOf("Galaktyka", "Słońce z planetami", "Mgławica", "Czarna dziura"), 1, "To Słońce i wszystko, co wokół niego krąży!"),
        Question("Która planeta ma najwięcej księżyców?", listOf("Jowisz", "Saturn", "Uran", "Neptun"), 1, "Saturn ma 146 znanych księżyców – rekord!"),
        Question("Skąd pochodzi energia Słońca?", listOf("Z węgla", "Z ropy", "Fuzji jądrowej", "Elektryczności"), 2, "Słońce łączy atomy wodoru w hel, uwalniając ogromną energię!")
    )
}
