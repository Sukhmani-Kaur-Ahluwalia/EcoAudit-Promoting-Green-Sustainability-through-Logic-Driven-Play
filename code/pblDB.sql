CREATE DATABASE IF NOT EXISTS eco_audit;
USE eco_audit;

-- Users table
CREATE TABLE IF NOT EXISTS users (
    login_id   VARCHAR(50)  PRIMARY KEY,
    password   VARCHAR(100) NOT NULL,
    total_score INT         DEFAULT 0,
    level       INT         DEFAULT 1,
    badges      JSON
);

CREATE TABLE IF NOT EXISTS eco_audit_questions (
    id INT PRIMARY KEY,
    question TEXT,
    option_a TEXT,
    points_a INT,
    option_b TEXT,
    points_b INT,
    option_c TEXT,
    points_c INT,
    option_d TEXT,
    points_d INT,
    correct_option CHAR(1)
);
 ALTER TABLE eco_audit_questions CONVERT TO CHARACTER SET utf8mb4;
INSERT INTO eco_audit_questions VALUES
(1,"You start your day 🌅 and step out of home. You see a plastic bag given at a nearby shop. What would YOU do?",
"Take it without thinking",0,
"Refuse politely and carry your own bag 🌿",10,
"Take it but reuse later",6,
"Feel awkward but accept it",3,'B'),


(2,"If YOU could speak as Earth 🌍 for a day, what would YOU most likely say?",
"I'm perfectly fine",6,
"Please reduce plastic usage 😭",10,
"I'm enjoying pollution",0,
"Nothing, I stay silent",3,'B'),

(3, "How did you commute today?",
 'Took a crowded train 🚂', 3,
 'Biked or walked 😎', 10,
 'Drove alone in a gas-gglover 🚗', 0,
 'Carpooled with friends 🚗👥', 6,
 'B'),
(4,"Best eco action?",
"Save plastic",10,
"Save electricity",10,
"Save food",10,
"All 👑",10,'D'),

(5,"Be honest 😄 You receive groceries. What usually happens with the carry bag?",
"I reuse it later 🌿",10,
"I store it but rarely reuse",6,
"I throw it after use",0,
"I don’t notice",3,'A'),

(6, "What's your waste game?",
 'Used a few disposables 🤷', 3,
 'Produced almost zero waste 🌿', 10,
 'Threw trash everywhere 😬', 0,
 'Segregated some waste ♻️', 6,
 'B'),

(7, "How loud was your night?",
 'Party vibes max 🔥', 0,
 'Chilled with friends, decent volume 👥', 6,
 'Had a quiet night 🌙️', 10,
 'Watched TV loud 🍿', 3,
 'C'),

(8, "What's your food vibe?",
 'Grabbed takeout 🍜', 3,
 'Plant-based home-cooked meal 🌿', 10,
 'Cooked with some local ingredients 🍲', 6,
 'Ate lots of packaged food 🍔', 0,
 'B'),

(9, "How's your digital life?",
 'Streamed movies all day 📺', 0,
 'Downloaded offline content 📥', 6,
 'Used gadgets moderately 📱', 3,
 'Went paperless, digital docs 📄', 10,
 'D'),
(10,"How's your morning started today?","Blasted loud music 🔥",0,"Had normal chatter 👥",6,"Practiced yoga with chill vibes 🧘",10,"Listened to loud music 🎧",3,'C'),
(11,"What's your go-to drink?","Plastic bottled soda 🥤",0,"Fresh juice with some packaging 🍹",6,"Coffee in a disposable cup ☕",3,"Water in a reusable bottle 💧",10,'D'),
(12,"How did you commute today?","Took a crowded train 🚂",3,"Carpooled with friends 🚗👥",6,"Biked or walked 😎",10,"Drove alone in a gas-gglover 🚗",0,'C'),
(13,"What's your shopping style?","Bought stuff with tons of plastic 🛍",0,"Opted for reusable bags 🛍️",6,"Grabbed some single-use items 🤷",3,"Shopped local, minimal packaging 🌿",10,'D'),
(14,"How's your energy consumption?","Sometimes turn off lights 🔌",3,"Switched to solar 🌞",10,"Used energy-efficient bulbs 💡",6,"Left lights on all day 🔌",0,'B'),
(15,"What's your waste game?","Produced almost zero waste 🌿",10,"Segregated some waste ♻️",6,"Used a few disposables 🤷",3,"Threw trash everywhere 😬",0,'A'),
(16,"How loud was your night?","Party vibes max 🔥",0,"Watched TV loud 🍿",3,"Had a quiet night 🌙️",10,"Chilled with friends, decent volume 👥",6,'C'),
(17,"What's your food vibe?","Ate lots of packaged food 🍔",0,"Plant-based home-cooked meal 🌿",10,"Grabbed takeout 🍜",3,"Cooked with some local ingredients 🍲",6,'B'),
(18,"How's your digital life?","Used gadgets moderately 📱",3,"Went paperless, digital docs 📄",10,"Downloaded offline content 📥",6,"Streamed movies all day 📺",0,'B'),
(19,"What's your water habit?","Took super long showers 🚿",0,"Saved every drop 🌿",10,"Had quick showers 🚿",6,"Used water kinda freely 💧",3,'B'),

(20,"How do you handle plastics?","Used tons of single-use plastics 🤯",0,"Mostly avoided plastics 🌿",6,"Carried reusable alternatives 👜",10,"Used some plastics 🤷",3,'C'),
(21,"What's your travel style?","Walked/biked 🚶‍♂️",10,"Carpooled or shared rides 🚗👥",6,"Took public transport 🚂",3,"Drove solo 🚗",0,'A'),
(22,"How's your fashion sense?","Bought lots of fast fashion 👕",0,"Grabbed some trendy pieces 👖",3,"Invested in sustainable brands 🌿",10,"Repurposed/old clothes 👕",6,'C'),
(23,"What's your stance on greens?","Planted a few 🌿",6,"Protected local flora 🌳",10,"Didn't plant any 😐",3,"Cut down trees 🌳",0,'B'),
(24,"How noisy was your morning?","Had normal chatter 👥",3,"Practiced silence 🙏",10,"Listened to nature 🌿",6,"Blasted music 🔥",0,'B'),
(25,"What's your snack plan?","Had some processed snacks 🍪",3,"Snacked on fruits 🍎",6,"Grabbed junk food snack 🍟",0,"Carried reusable containers 🥗",10,'D'),
(26,"How's your gadget usage?","Minimized screen time 📵",10,"Used eco-friendly accessories 🌿",6,"Upgraded devices often 📱",3,"Used lots of disposables 📱",0,'A'),
(27,"What's your commute impact?","Drove a gas-gglover 🚗",0,"Carpooled 🚗👥",6,"Walked/biked 🚶‍♂️",10,"Took public transport 🚂",3,'C'),
(28,"How do you handle food waste?","Shared excess food 🍲",10,"Reduced waste 🌿",6,"Composted some ♻️",3,"Threw food away 🍔",0,'A'),
(29,"What's your lighting vibe?","Used natural light 🌞",10,"Used energy bulbs 💡",3,"Switched to LED 🌞",6,"Left lights on 🔌",0,'A'),

(30,"How do you handle paper waste?","Printed tons of paper 📄",0,"Reused paper ♻️",6,"Went digital 🌿",10,"Used some paper 🤷",3,'C'),
(31,"What's your cleaning habit?","Made own cleaning products 🧹",10,"Used harsh chemicals 🧹",0,"Used some eco-friendly products 🌿",3,"Minimized cleaning needs 🌿",6,'A'),
(32,"How's your shopping frequency?","Shopped sometimes 🤷",3,"Bought only necessities 🛍️",10,"Planned purchases 🌿",6,"Shopped impulsively 🛍",0,'B'),
(33,"What's your stance on meat consumption?","Had some meat 🤷",3,"Opted for plant-based 🌿",10,"Reduced meat intake 🍲",6,"Ate lots of meat 🍔",0,'B'),
(34,"How do you handle electronics?","Upgraded often 📱",3,"Repaired devices 🛠️",6,"Recycled electronics ♻️",10,"Threw away old gadgets 📱",0,'C'),
(35,"How do you handle food packaging?","Grabbed packaged snacks 🤷",3,"Used reusable containers 🥗",6,"Bought in bulk, minimal packaging 🌿",10,"Used lots of plastic wrap 🍔",0,'C'),
(36,"What's your bathroom habit?","Used some paper products 🤷",3,"Used eco-friendly products 🌿",6,"Used bidet/waved paper 🌿",10,"Used lots of paper products 🚽",0,'C'),
(37,"How's your recycling game?","Recycled some ♻️",3,"Segregated waste properly ♻️",6,"Recycled almost everything 🌿",10,"Threw everything in trash 😬",0,'C'),
(38,"What's your stance on air travel?","Took flights often ✈️",0,"Avoided flying 🌿",10,"Opted for trains/buses 🚂",6,"Took a few flights ✈️",3,'B'),
(39,"How do you handle clothes washing?","Washed clothes daily with hot water 🛍",0,"Washed clothes often with normal water 🤷",3,"Used eco-friendly detergent 🌿",10,"Washed clothes less, cold water 🌿",6,'C'),
(40,"What's your gadget charging habit?","Used some energy-efficient chargers 💡",3,"Used solar chargers 🌞",10,"Left chargers plugged in 🔌",0,"Unplugged chargers when not in use 🔌",6,'B'),
(41,"How's your shopping bag habit?","Used tons of plastic bags 🛍",0,"Mostly used reusable bags 🛍️",6,"Carried cloth bags 🌿",10,"Used some reusable bags 🤷",3,'C'),
(42,"What's your stance on bottled water?","Used a refillable bottle 💧",6,"Bought lots of bottled water 🥤",0,"Carried a reusable water bottle 🌿",10,"Bought some bottled water 🤷",3,'C'),
(43,"How do you handle leftover food?","Stored in plastic containers 🤷",3,"Composted food waste 🌿",10,"Stored in reusable containers 🥗",6,"Threw it away 🍔",0,'B'),
(44,"What's your lighting plan?","Used some energy bulbs 💡",3,"Used natural light 🌞",10,"Switched to LED 🌞",6,"Used lots of incandescent bulbs 🔌",0,'B'),
(45,"How's your transportation frequency?","Drove everywhere 🚗",0,"Walked/biked short distances 🚶‍♂️",10,"Used public transport sometimes 🚂",3,"Carpooled or shared rides 🚗👥",6,'B'),
(46,"What's your stance on microplastics?","Used products with microbeads 🧴",0,"Mostly avoided microplastics 🌿",6,"Chose microplastic-free products 🌿",10,"Used some products with microplastics 🤷",3,'C'),
(47,"How do you handle paper usage?","Used some paper 🤷",3,"Printed tons of paper 📄",0,"Reused paper ♻️",6,"Went digital 🌿",10,'D'),
(48,"What's your cleaning product choice?","Used harsh chemicals 🧹",0,"Used some eco-friendly products 🌿",3,"Made own cleaning products 🧹",10,"Minimized cleaning needs 🌿",6,'C'),
(49,"How's your food sourcing?","Bought lots of imported food 🍔",0,"Mostly bought local/seasonal 🌿",6,"Grafted own food 🌿",10,"Bought some local food 🤷",3,'C'),
(50,"What's your stance on disposables?","Used some disposables 🤷",3,"Used lots of disposables 🤯",0,"Mostly avoided disposables 🌿",6,"Carried reusable alternatives 👜",10,'D');

