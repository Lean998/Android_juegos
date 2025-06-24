package com.example.juegos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBPartidaHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "juegos.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PARTIDAS = "partidas";
    public static final String TABLE_JUEGOS = "juegos";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JUGADOR = "jugador";
    public static final String COLUMN_IDJUEGO = "idJuego";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_GENERO = "genero";
    public static final String COLUMN_DIFICULTAD = "dificultad";
    public static final String COLUMN_NIVELES = "niveles";
    public static final String COLUMN_NIVEL = "nivel";
    public static final String COLUMN_PUNTAJE = "puntaje";
    public static final String COLUMN_FECHA = "fecha";

    private static final String TABLE_CREATE_JUEGOS =
            "CREATE TABLE " + TABLE_JUEGOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " VARCHAR(50) NOT NULL, " +
                    COLUMN_DESCRIPCION + " VARCHAR(100) NOT NULL, " +
                    COLUMN_GENERO + " VARCHAR(50) NOT NULL, " +
                    COLUMN_NIVELES + " INTEGER NOT NULL " +
                    ");";

    private static final String TABLE_FILL_JUEGOS =
            "INSERT INTO " + TABLE_JUEGOS + " (" +
                    COLUMN_NOMBRE + ", " +
                    COLUMN_DESCRIPCION + ", " +
                    COLUMN_GENERO + ", " +
                    COLUMN_NIVELES +
                    ") VALUES " +
                    "('Angry Birds', 'La supervivencia de los Angry Birds está en juego. Véngate de los codiciosos cerdos que han robado sus huevos. Usa los poderes únicos de cada pájaro para desmoronar las defensas de los cerdos. Angry Birds presenta un sistema de juego desafiante y dinámico que te permite jugar horas y horas. Resolver cada nivel requiere lógica, habilidad y arrojo.', 'Estrategia', 10), " +
                    "('Kingdom Rush Frontiers TD', 'Un juego tower defense donde los jugadores defienden tierras exóticas de varios enemigos como dragones, plantas devoradoras de hombres y criaturas del inframundo utilizando una variedad de torres, héroes y habilidades especiales.', 'Estrategia', 7)," +
                    "('Escapists 2', 'The Escapists 2 es una emocionante y desternillante experiencia de estrategia en mundo abierto que presenta el complicado desafío de escapar de una prisión. ¡Crea tu propio presidiario con cientos de opciones de personalización, ve por tu cuenta o conspira con hasta 3 de tus amigos para idear la fuga definitiva en multijugador local!', 'Estrategia', 5)," +
                    "('Plants vs Zombies 2', 'Juega a la aventura de acción y estrategia en la que podrás conocer, saludar y derrotar a legiones de zombis alocados desde el amanecer de los tiempos hasta el ocaso de los días. Reúne un ejército de plantas increíbles, cárgalas hasta los topes de Nutrientes y urde el plan definitivo para proteger tu cerebro.', 'Estrategia', 10)," +
                    "('Nightfall Kingdom Frontier TD', 'Sumérgete en una épica aventura de defensa de torres, donde demostrarás tu estrategia en la gestión de recursos y la defensa de tu reino en un RPG medieval de fantasía. Construye, defiende y lucha contra hordas interminables para sobrevivir en este mundo medieval.', 'Estrategia', 10)," +
                    "('Breach Wanderers', 'Una nueva experiencia Roguelike Deckbuilding\n" +
                    "Embárcate en una emocionante aventura con esta nueva versión de Roguelike Deckbuilding. Lucha contra monstruosas criaturas que emergen de la Brecha, usando y desarrollando una estrategia basada en cartas mágicas, poderes y héroes. A medida que avanzas en las misiones y mejoras tu ciudad, elabora estrategias con sabiduría para construir tu destino.', 'Estrategia', 10)," +
                    "('Deep Town', 'Deep Town es un juego de estrategia de ciencia ficción en el que juegas como una Inteligencia Artificial, con el único propósito de acumular recursos, construir y replicarse.', 'Estrategia', 10)," +
                    "('European War 6', 'El desarrollo de tecnologías como la máquina de vapor, el ferrocarril y los barcos ha llenado el mundo de nuevas oportunidades.\n" +
                    "Insatisfechos con los arreglos coloniales del Viejo Mundo, los imperios en ascenso se están preparando para competir. Inevitablemente, la guerra se convierte en el resultado de intereses en conflicto.\n" +
                    "El mundo entero no esperaba que la guerra creciera a una escala tan grande.\n" +
                    "¿Podrían genios militares como Hindenburg, Ludendorff, Pétain, Foch, Haig y otros poner fin a la guerra?\n" +
                    "Revive intensas batallas militares históricas en la Primera Guerra Mundial. ¡Comandante! ¡Es hora de usar la estrategia ganadora y comenzar tu legendario viaje militar!', 'Estrategia', 10)," +
                    "('Slice & Dice', 'Controla a 5 héroes, cada uno con un dado único. Combate contra monstruos a través de 20 niveles e intenta vencer al jefe final. Si pierdes una sola pelea, tienes que empezar de nuevo, así que ten cuidado (¡y mucha suerte!).', 'Estrategia', 10)," +

                    "('Smash Hit', 'Un viaje surrealista a través de una dimensión mística por la que tendrás que moverte siguiendo el ritmo del sonido y la música y aplastar lo que encuentres a tu paso. Para esta experiencia, necesitarás precisión, concentración y buen ritmo para viajar lo más lejos que puedas y romper los hermosos objetos de cristal que encontrarás por el camino.', 'Disparos', 10)," +
                    "('Into the Dead 2', 'Ábrete camino a través del apocalipsis zombi en una carrera para salvar a tu familia. Equípate con todo un arsenal de armas potentes y haz todo lo que haga falta para sobrevivir. Mutila, acribilla y masacra a los muertos, ¡lo importante es seguir avanzando! En un mundo donde nadie está a salvo, ¿hasta dónde llegarás para sobrevivir?\n" +
                    "\n" +
                    "Sigue viviendo la pesadilla con los eventos de historia, que incluyen una terrorífica precuela a La noche de los muertos vivientes y una ampliación al mundo de Cazafantasmas.\n', 'Disparos', 10)," +
                    "('Soul Knight', '“En una época de espadas y armas de fuego, unos alienígenas de alta tecnología han robado la piedra mágica que mantiene el equilibrio del mundo. El mundo pende de un fino hilo. Todo depende de que recuperes la piedra mágica...”\n" +
                    "\n" +
                    "La verdad es que no podemos seguir inventándonoslo todo. ¡Así que vamos a disparar a los alienígenas!', 'Disparos', 10)," +
                    "('Ramboat', 'Juego de Guerra Arcade y Sin Internet, mezcla de forma única los géneros arcade, shooter y runner. Ramboat, un adictivo juego de guerra arcade con misiones y aventura sin fin. Ayuda a Mambo a escapar de cientos de enemigos, saltando y conduciendo a máxima velocidad mientras disparas poderosas armas. ¿Te gusta correr y la acción? ¿saltar y disparar? Entonces AMARÁS Ramboat!', 'Disparos', 10)," +
                    "('Sniper 3D', '¡Prepárate para desatar la experiencia definitiva de Sniper 3d con el juego de disparos más emocionante y lleno de acción de todos los tiempos! Sniper 3d es el juego de armas gratuito definitivo que te pone en la piel de un asesino mortal. Sumérgete en misiones fuera de línea de alta intensidad y muestra tus habilidades de tiro en esta aventura de francotiradores que te llenará de adrenalina. ¿Eres el mejor tirador? ¡Demuéstralo en Sniper 3d!', 'Disparos', 10)," +
                    "('Hitman Sniper', 'CONVIÉRTETE EN EL MEJOR ASESINO SILENCIOSO\n" +
                    "Métete en la piel del agente 47 en Hitman: Sniper y descubre la experiencia de francotirador más auténtica para móviles.', 'Disparos', 10)," +

                    "('Crossy Road', '¿Por qué cruzó la calle la gallina?\n" +
                    "¿Por qué la paloma dejó ESO allí?\n" +
                    "¿Por qué el marciano 115 abdujo esa vaca?\n" +
                    "¿Por qué el unicornio se comió todas esas golosinas?\n" +
                    "\n" +
                    "Crossy Road es el juego de arcade sin fin de 8 bits que dio inicio a todo. Colecciona personajes personalizados y recorre autopistas, ferrocarriles, ríos y mucho más.\n" +
                    "\n" +
                    "Crossy Road es el saltarín incansable de los videojuegos que no querrás dejar jamás.', 'Acción', 10)," +
                    "('Fruit Ninja', 'Corta fruta, no cortes bombas, ¡eso es todo lo que necesitas saber para comenzar con el adictivo juego de acción Fruit Ninja! Desafíate a ti mismo y observa hasta dónde puedes llegar en el modo Clásico, supera tu puntuación en el modo Arcade o simplemente practica tus habilidades de corte de frutas en el modo Zen. Una amplia gama de espeadas y dojos están a tu disposición para ayudarte en tu camino a la cumbre.', 'Acción', 10)," +
                    "('ZigZag', '¡Permanece en la pared y haz tantos zigzags como puedas!\n" +
                    "\n" +
                    "Simplemente toca la pantalla para cambiar la dirección de la pelota. ¡Intenta no caerte de los bordes!\n" +
                    "\n" +
                    "¿Qué tan lejos puedes llegar?', 'Acción', 10)," +
                    "('ICEY', 'Desarrollado por FantaBlade y publicado por X.D. Network, ICEY es un side scroller 2D de acción. Siguiendo las órdenes del narrador, verás el mundo de ICEY a través de sus ojos y te embarcarás en una aventura para conocer la verdad sobre su mundo.', 'Acción', 10)," +
                    "('Magic Rampage', 'Un emocionante plataformas que combina el género RPG con una jugabilidad repleta de acción frenética. Magic Rampage dispone de personalización de personajes y decenas de armas para equiparte, desde cuchillos hasta báculos mágicos. Cada mazmorra le presenta al jugador nuevos obstáculos, enemigos y zonas secretas que explorar. Busca los niveles extra, intenta alzarte con la victoria en el modo Supervivencia, alíate con PNJ amistosos y enfréntate a desafiantes combates de jefes.', 'Acción', 10)," +
                    "('Cookies Must Die', 'Conoce a Jack. ¡Es un agente súper secreto con poderes especiales incorporados en su cuerpo por científicos del gobierno!\n" +
                    "Tiene que detener a un grupo de malvadas galletas mutantes y a sus poderosos jefes, ¡antes de que reduzcan su ciudad a escombros!', 'Acción', 10)," +

                    "('Score! Hero', '¿Alguna vez has soñado con convertirte en una superestrella del fútbol mundial? ¿Prefieres un juego sencillo con mecánicas de puzle que ya han disfrutado millones de usuarios? ¿Un juego de fútbol con acción 3D realista que te brinda la oportunidad de fichar por tu equipo de fútbol favorito, ¡o hasta de jugar en representación de tu país!?\n" +
                    "\n" +
                    "Sabemos que tienes talento, precisión y habilidad. Quieres compararte con el mejor. No dudes en jugar ahora... ¡gratis!', 'Deportes', 10)," +
                    "('PBA Bowling Challenge', 'Compite contra los grandes nombres de la PBA Bowling en más de una docena de diferentes lugares o desafía a tus amigos en el modo multijugador en línea en tiempo real!\n" +
                    "\n" +
                    "Mejora tus armas y desbloquea decenas de logros a medida que avanza a través de docenas de torneos individuales y tablas de clasificación multijugador!\n" +
                    "\n" +
                    "Utilice juego cambiantes bolas especiales, como el Ball Lightning, el Balón de Split, y el Balón de bomba para ataques explosivos!\n" +
                    "\n" +
                    "Se trata de PBA Bowling Challenge!', 'Deportes', 10)," +
                    "('Pesca y Vida', 'La pesca es sanar tu corazón.\n" +
                    "\n" +
                    "Te presentamos el juego más hermoso y tranquilo que estás cansado y agotado de la vida diaria.\n" +
                    "\n" +
                    "Disfrute de la pesca de ballenas con un simple control mientras escucha el sonido de las olas ASMR en el hermoso mar.', 'Deportes', 10)," +
                    "('True Skate', 'La sensación más cercana a la del skateboarding real, ya que la interacción del jugador con el skateboard está completamente impulsada por el sistema de FÍSICAS instantánea y unificada. El skateboard reacciona al instante, ya que el pie y el stick se sienten totalmente conectados.', 'Deportes', 10)," +
                    "('Stick Tennis', 'Fácil de jugar, difícil de dominar, Stick Tennis ofrece una jugabilidad rápida y fluida y un enorme rango de jugadores, con su estilo y características propias, para desbloquear y jugar.\n" +
                    "\n" +
                    "Con simplemente deslizar un dedo podrás realizar cruzados ganadores. Además Stick Tennis posee un sofisticado motor de físicos que beneficia el juego táctico.', 'Deportes', 10)," +
                    "('Football Manager 2023 Mobile', 'Un juego en el que puedes gestionar un equipo de fútbol que aspira a ganar en las grandes ligas.', 'Deportes', 10)," +

                    "('Fallout Shelter', 'CREA EL REFUGIO PERFECTO\nSUPERVISA UNA FLORECIENTE COMUNIDAD SUBTERRÁNEA\nPROSPERA\nEXPLORA EL YERMO\nPROTEGE TU REFUGIO\n" +
                    "Vault-Tec te ofrece todo cuanto necesitas, pero el resto corre de tu cuenta. ¿A qué esperas? Comienza a crear tu refugio hoy mismo, GRATIS.', 'Simulación', 10)," +
                    "('Plague Inc.', '¿Podrás infectar el mundo? Plague Inc. es una mezcla única de estrategia y simulación terroríficamente realista.\n', 'Simulación', 10)," +
                    "('This War of Mine', 'En This War Of Mine no juegas como un soldado de élite, sino como un grupo de civiles que intentan sobrevivir en una ciudad sitiada; luchando contra la falta de alimentos, medicamentos y el peligro constante de francotiradores y carroñeros hostiles. El juego ofrece una experiencia de guerra vista desde un ángulo completamente nuevo.', 'Simulación', 10)," +
                    "('Idle Digging', '¡Bienvenido a Idle Digging, el mejor juego inactivo para móviles en el que te conviertes en un maestro excavador y te embarcas en una emocionante aventura minera! Descubre tesoros, artefactos raros y recursos valiosos a medida que cavas más profundo en las profundidades de la tierra.', 'Simulación', 10)," +
                    "('SimCity BuildIt', '¡Bienvenido, alcalde! Conviértete en el héroe de tu propia ciudad mientras diseñas y creas una metrópolis llena de vida. Tú tomas todas las decisiones a medida que tu ciudad se hace más grande y compleja. Hazlo con cabeza para hacer felices a tus ciudadanos y mantener el crecimiento de tus rascacielos. Luego comercia, chatea, compite y únete a clubes con alcaldes amigos. ¡Construye la ciudad más extraordinaria!', 'Simulación', 10)," +
                    "('Pou', '¿Tienes lo que se necesita para cuidar de tu mascota alienigena?! Alimentalo, limpialo, juega con él y observalo crecer mientras sube de nivel y desbloquea diferentes fondos de habitación y trajes para disfrutar tu y tu Pou. ¿Cómo vas a personalizar a tu Pou?', 'Simulación', 10)," +

                    "('Stardew Valley', '¡El juego de rol de agricultura de éxito de ConcernedApe llega a móviles!\n" +
                    "\n" +
                    "¡Múdate al campo y cultiva una nueva vida en este galardonado juego de rol de agricultura con final abierto! Reconstruido desde los cimientos para móviles.\n" +
                    "\n" +
                    "¡Ahora con nuevo contenido al acabar el juego! Descubre más eventos de corazones, nuevos cultivos, nuevas mascotas y mucho más...', 'Rol', 10)," +
                    "('Oceanhorn', 'Un día te levantas y encuentras una carta de tu padre diciendo que se ha ido...\n" +
                    "La única pista que tienes es un viejo diario y un misterioso collar. ¿Qué habrá pasado?\n" +
                    "\n" +
                    "Explora las islas de los Mares inexplorados, un mundo lleno de peligros, rompecabezas y secretos. Enfréntate a monstruos, aprende a usar la magia y descubre tesoros antiguos que te ayudarán en tu misión. Usa todo tu ingenio y tus habilidades para revelar los misterios del antiguo reino de Arcadia y del monstruo marino llamado Oceanhorn.', 'Rol', 10)," +
                    "('Evoland', 'Según progresas a lo largo del juego, irás desbloqueando nuevas tecnologías, sistemas de juego y gráficos cada vez mejores. Partiendo de gráficos monocromáticos y batallas por turnos hasta librar combates contra enemigos finales a tiempo real con gráficos totalmente en 3D, vive con Evoland la evolución de los juegos de aventuras en una experiencia llena de humor y guiños a momentos míticos de juegos clásicos.', 'Rol', 10)," +
                    "('EvoCreo', 'EvoCreo: Monstruo de bolsillo. Un juego de aventura para ese entrenador de monstruos que quieres ser', 'Rol', 10)," +
                    "('Grim Quest', 'Grim Quest intenta combinar la esencia de los juegos de rol de mesa, el clásico \"dungeon crawling\" y las mecánicas de roguelike, junto con un sistema de combate por turnos clásico, en una experiencia accesible y entretenida. Por su enfoque en la narrativa escrita, su detallado desarrollo del mundo y su abundante historia, Grim Quest puede asemejarse a una campaña de Dungeons and Dragons en solitario o incluso a un libro de \"elige tu propia aventura\".', 'Rol', 10)," +
                    "('Almora Darkosen RPG', '¡Embárcate en un viaje a un vasto mundo en Almora Darkosen RPG, un clásico juego de hack and slash de estilo retro!\n" +
                    "Sumérgete en un enorme mundo abierto lleno de secretos y peligros, pasa incontables horas explorando, luchando y desarrollando a tu personaje.', 'Rol', 10)"

            ;





    private static final String TABLE_CREATE_PARTIDAS =
            "CREATE TABLE " + TABLE_PARTIDAS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_JUGADOR + " VARCHAR(50) NOT NULL, " +
                    COLUMN_IDJUEGO + " INTEGER NOT NULL, " +
                    COLUMN_DIFICULTAD + " VARCHAR(50), " +
                    COLUMN_NIVEL + " INTEGER NOT NULL, " +
                    COLUMN_PUNTAJE + " INTEGER NOT NULL, " +
                    COLUMN_FECHA + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    "CONSTRAINT fk_partidas_idJuego FOREIGN KEY (" + COLUMN_IDJUEGO + " ) REFERENCES juegos(" + COLUMN_ID + ")" +
                    ");";

    public DBPartidaHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_JUEGOS);
        db.execSQL(TABLE_FILL_JUEGOS);
        db.execSQL(TABLE_CREATE_PARTIDAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JUEGOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTIDAS);
        onCreate(db);
    }
}
