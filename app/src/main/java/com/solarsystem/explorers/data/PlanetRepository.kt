package com.solarsystem.explorers.data

object PlanetRepository {
    val planets = listOf(
        Planet(
            id = "sun", name = "Sun", number = "Central Star", emoji = "☀️",
            desc = "The Sun is a huge star at the center of our system. It gives us the light and heat that allow us to live!",
            facts = listOf(
                "🔥 Surface temperature is about 5,500 °C!",
                "⚖️ The Sun's mass is 99.8% of the mass of the entire Solar System.",
                "🌍 Over a million Earths could fit inside the Sun!",
                "⏳ The Sun is about 4.6 billion years old."
            ),
            wow = "Light from the Sun takes 8 minutes to reach Earth!"
        ),
        Planet(
            id = "mercury", name = "Mercury", number = "1st planet from the Sun", emoji = "🪨",
            desc = "Mercury is the smallest planet and closest to the Sun. Despite being close to the Sun, it's freezing cold there at night!",
            facts = listOf(
                "🌡️ Temperature ranges from –180 °C (night) to +430 °C (day)!",
                "📅 A year lasts only 88 Earth days.",
                "🌙 Mercury is only slightly larger than the Moon.",
                "💨 Almost no atmosphere – no protection from space."
            ),
            wow = "A day on Mercury lasts LONGER than a year! One rotation = 59 Earth days, but a year = 88 days."
        ),
        Planet(
            id = "venus", name = "Venus", number = "2nd planet from the Sun", emoji = "☁️",
            desc = "Venus is almost as large as Earth, but it is the hottest planet in the Solar System! Thick clouds trap heat like a blanket.",
            facts = listOf(
                "🔥 Surface temperature: as much as 465 °C – as hot as an oven × 5!",
                "🔄 It rotates in the opposite direction to most planets.",
                "☁️ The clouds contain sulfuric acid.",
                "🌟 The brightest object in the night sky (after the Moon)."
            ),
            wow = "A day on Venus lasts longer than a year! It spins slowly but orbits the Sun quickly."
        ),
        Planet(
            id = "earth", name = "Earth", number = "3rd planet from the Sun", emoji = "🌍",
            desc = "Our home! Earth is the third planet from the Sun and the only one where people, animals, and plants live.",
            facts = listOf(
                "💧 Over 70% of the surface is oceans!",
                "🌙 It has one natural satellite – the Moon.",
                "🌡️ Ideal temperature for life thanks to the atmosphere.",
                "🛡️ The magnetic field protects us from cosmic radiation."
            ),
            wow = "Earth is the only place in the universe where life is confirmed to exist!"
        ),
        Planet(
            id = "moon", name = "Moon", number = "Earth's Satellite", emoji = "🌙",
            desc = "The Moon is our only natural satellite. Although it shines at night, it doesn't have its own light – it only reflects sunlight!",
            facts = listOf(
                "👣 The first human stood on the Moon in 1969.",
                "🌑 The Moon causes tides in Earth's oceans.",
                "🧊 There may be frozen water at the Moon's poles.",
                "🌬️ There is no atmosphere or wind on the Moon – astronauts' footprints will stay there for millions of years!"
            ),
            wow = "On the Moon, you would weigh 6 times less than on Earth! You could jump very high."
        ),
        Planet(
            id = "mars", name = "Mars", number = "4th planet from the Sun", emoji = "🔴",
            desc = "Mars is the Red Planet! It's colder and smaller than Earth. Scientists send rovers there to study it.",
            facts = listOf(
                "🔴 The red color comes from rust (iron oxide) on the surface.",
                "⛰️ Olympus Mons – the highest volcano in the Solar System (27 km!).",
                "🌬️ There are huge dust storms lasting for months.",
                "🤖 Rovers work on Mars: Curiosity and Perseverance!"
            ),
            wow = "A day on Mars lasts almost as long as on Earth – 24 hours and 37 minutes!"
        ),
        Planet(
            id = "jupiter", name = "Jupiter", number = "5th planet from the Sun", emoji = "🪐",
            desc = "Jupiter is a gas giant and the largest planet. It has no solid surface – it is built mainly of hydrogen and helium.",
            facts = listOf(
                "🏆 The largest planet – 1,300 Earths could fit inside!",
                "🌀 The Great Red Spot is a storm that has lasted for over 400 years.",
                "🛸 It has at least 95 moons!",
                "⚡ Winds reach 620 km/h."
            ),
            wow = "Jupiter acts like a 'space vacuum cleaner' – its gravity attracts asteroids and protects Earth!"
        ),
        Planet(
            id = "saturn", name = "Saturn", number = "6th planet from the Sun", emoji = "💍",
            desc = "Saturn is a gas giant with magnificent rings. The rings are built of pieces of ice and rock!",
            facts = listOf(
                "💍 The rings extend 280,000 km from the planet!",
                "🪶 It's so light that it could float on water!",
                "🌙 It has 146 known moons.",
                "💨 Winds reach 1,800 km/h!"
            ),
            wow = "Saturn is the only planet that would float on water – it is less dense than H₂O!"
        ),
        Planet(
            id = "uranus", name = "Uranus", number = "7th planet from the Sun", emoji = "🔵",
            desc = "Uranus is an ice giant. It is tilted so much that it almost lies on its side – it spins like a rolling ball!",
            facts = listOf(
                "↔️ It rotates almost horizontally – axis tilted by 98°!",
                "🥶 The coldest planet – down to –224 °C.",
                "💍 It has 13 faint rings.",
                "📅 A year lasts 84 Earth years."
            ),
            wow = "Uranus lies on its side! Probably a huge asteroid collided with it a long time ago."
        ),
        Planet(
            id = "neptune", name = "Neptune", number = "8th planet from the Sun", emoji = "🌊",
            desc = "Neptune is the last planet in the Solar System. It is very far from the Sun and incredibly windy!",
            facts = listOf(
                "💨 Strongest winds in the Solar System – 2,100 km/h!",
                "🔭 Discovered through mathematics before anyone saw it through a telescope!",
                "📅 A year lasts 165 Earth years.",
                "🌊 Named after the Roman god of the sea."
            ),
            wow = "Neptune was discovered by mathematical calculations before a telescope was used – science is amazing!"
        )
    )

    val badges = listOf(
        Badge("first_quiz", "🎓", "First Step", "Complete your first quiz."),
        Badge("perfect", "💯", "Perfection!", "Get 10/10 in a quiz."),
        Badge("explorer", "🔭", "Great Explorer", "Click on each of the 8 planets."),
        Badge("streak3", "🔥", "On Fire!", "3 correct answers in a row."),
        Badge("streak5", "⚡", "Lightning!", "5 correct answers in a row."),
        Badge("quick", "🚀", "Rocket!", "Answer in less than 5 seconds."),
        Badge("pts100", "⭐", "Cosmonaut", "Earn 100 points."),
        Badge("pts300", "🌟", "Planet Explorer", "Earn 300 points."),
        Badge("pts500", "🏆", "Space Master", "Earn 500 points."),
        Badge("quiz3", "🎮", "Gamer", "Play the quiz 3 times.")
    )

    val levels = listOf(
        Level(0, "📚", "Student"),
        Level(50, "👨\u200D🚀", "Astronaut"),
        Level(150, "🧑\u200D🚀", "Cosmonaut"),
        Level(300, "🔭", "Planet Explorer"),
        Level(500, "🏆", "Space Master")
    )

    val questions = listOf(
        Question("Which planet is closest to the Sun?", listOf("Venus", "Mercury", "Earth", "Mars"), 1, "Mercury is the first planet from the Sun!"),
        Question("Which planet is the largest in the Solar System?", listOf("Saturn", "Neptune", "Uranus", "Jupiter"), 3, "Jupiter fits over 1,300 Earths inside!"),
        Question("Which planet is famous for its beautiful rings?", listOf("Jupiter", "Saturn", "Uranus", "Neptune"), 1, "Saturn has the best-visible rings made of ice and rocks!"),
        Question("How many planets are in the Solar System?", listOf("7", "9", "8", "10"), 2, "Since 2006, officially 8 planets!"),
        Question("Which planet is red?", listOf("Mars", "Venus", "Mercury", "Saturn"), 0, "Mars is red because of rust (iron oxide)!"),
        Question("On which planet do we live?", listOf("Mars", "Venus", "Earth", "Mercury"), 2, "We live on Earth – the 3rd planet from the Sun!"),
        Question("Which planet is the last – farthest from the Sun?", listOf("Uranus", "Saturn", "Neptun", "Jupiter"), 2, "Neptune is the eighth and last planet!"),
        Question("Which planet is the hottest?", listOf("Mercury", "Venus", "Mars", "Jupiter"), 1, "Venus (465 °C) is hotter than Mercury due to the greenhouse effect!"),
        Question("Which planet spins lying on its side?", listOf("Neptune", "Saturn", "Uranus", "Jupiter"), 2, "Uranus's axis is tilted by 98°!"),
        Question("How many moons does Earth have?", listOf("2", "0", "3", "1"), 3, "Earth has one moon – the Moon!"),
        Question("What is the name of the highest volcano in the Solar System?", listOf("Olympus Mons", "Everest", "Vesuvius", "Etna"), 0, "Olympus Mons on Mars is 27 km high!"),
        Question("Which planet could float on water?", listOf("Jupiter", "Uranus", "Saturn", "Neptune"), 2, "Saturn is less dense than water!"),
        Question("What color is the planet Uranus?", listOf("Red", "Purple", "Azure", "Green"), 2, "Methane in the atmosphere gives Uranus its azure color!"),
        Question("Which planet has the Great Red Spot?", listOf("Mars", "Saturn", "Neptune", "Jupiter"), 3, "It's a storm on Jupiter that has lasted for over 400 years!"),
        Question("Which planet lies between Earth and Jupiter?", listOf("Saturn", "Venus", "Mars", "Mercury"), 2, "Mars (4th) is between Earth (3rd) and Jupiter (5th)!"),
        Question("How long does one year last on Earth?", listOf("365 days", "24 hours", "200 days", "12 days"), 0, "365 days – that's how long Earth needs to orbit the Sun!"),
        Question("Which planet is the smallest?", listOf("Mars", "Mercury", "Venus", "Neptune"), 1, "Mercury is the smallest planet in the Solar System!"),
        Question("What is the Solar System?", listOf("Galaxy", "The Sun with planets", "Nebula", "Black hole"), 1, "It's the Sun and everything that orbits it!"),
        Question("Which planet has the most moons?", listOf("Jupiter", "Saturn", "Uranus", "Neptune"), 1, "Saturn has 146 known moons – a record!"),
        Question("Where does the Sun's energy come from?", listOf("From coal", "From oil", "Nuclear fusion", "Electricity"), 2, "The Sun combines hydrogen atoms into helium, releasing enormous energy!")
    )
}
