-- ============================
-- Seed Habitats (5 rows)
-- ============================

INSERT INTO habitats (name) VALUES
    ('Arctic Dome'),
    ('Moss Caverns'),
    ('Crimson Dunes'),
    ('Nebula Grove'),
    ('Obsidian Marsh');

-- ============================
-- Seed Creatures (20 rows)
-- ============================

INSERT INTO creatures (name, status, habitat_id) VALUES
    ('Frostfang', 'Healthy', 1),
    ('Glacier Stalker', 'Injured', 1),
    ('Snowbound Lynx', 'Healthy', 1),
    ('Polar Wraith', 'Critical', 1),

    ('Mosslight Vulpin', 'Healthy', 2),
    ('Cavern Crawler', 'Healthy', 2),
    ('Glowcap Serpent', 'Injured', 2),
    ('Burrowback Grazer', 'Healthy', 2),

    ('Sandstrider Raptor', 'Healthy', 3),
    ('Dune Howler', 'Injured', 3),
    ('Crimson Scarab', 'Healthy', 3),
    ('Ashback Lizard', 'Healthy', 3),

    ('Starbloom Moth', 'Healthy', 4),
    ('Nebula Stag', 'Healthy', 4),
    ('Voidpetal Hare', 'Injured', 4),
    ('Cosmic Finch', 'Healthy', 4),

    ('Marshlurker', 'Healthy', 5),
    ('Obsidian Croaker', 'Injured', 5),
    ('Tarback Tortoise', 'Healthy', 5),
    ('Blackwater Eel', 'Critical', 5);