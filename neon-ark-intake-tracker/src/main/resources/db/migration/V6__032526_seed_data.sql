-- Habitats
UPDATE habitats
SET
    location = 'Sector A - Moss Caverns',
    min_temp_c = 8,
    max_temp_c = 18,
    created_at = NOW()
WHERE biome = 'FOREST';

INSERT INTO habitats (biome, location, min_temp_c, max_temp_c, created_at)
VALUES
    ('DESERT', 'Sector B - Glass Dune Expanse', 22, 46, NOW()),
    ('TUNDRA', 'Sector C - Frostbite Plateau', -28, -8, NOW()),
    ('SWAMP', 'Sector D - Mirefall Basin', 10, 22, NOW()),
    ('MOUNTAIN', 'Sector E - Shatterpeak Ridge', -6, 12, NOW()),
    ('GRASSLAND', 'Sector F - Whisperwind Prairie', 5, 28, NOW()),
    ('COASTAL', 'Sector G - Brinewatch Cliffs', 12, 30, NOW()),
    ('RAINFOREST', 'Sector H - Emerald Canopy', 18, 32, NOW()),
    ('CAVE', 'Sector I - Obsidian Hollows', 2, 10, NOW()),
    ('VOLCANIC', 'Sector J - Ashenflow Crater', 40, 65, NOW());

-- Creatures
INSERT INTO creatures (name, species, danger_level, condition, notes, habitat_id, created_at)
VALUES
    ('Nyx', 'Void Fox', 'HIGH', 'QUARANTINED', 'Avoid bright light', 1, NOW()),
    ('Mosslight Vulpin', 'Vulpin lumina', 'LOW', 'STABLE', 'Bioluminescent fur; shy but curious.', 1, NOW()),
    ('Barkback Grazer', 'Cervid arboris', 'LOW', 'STABLE', 'Feeds on fungal mats; social.', 6, NOW()),
    ('Glass Serpent', 'Serpentes vitra', 'MEDIUM', 'OBSERVED', 'Translucent scales; heat‑sensitive.', 2, NOW()),
    ('Frostmane Stalker', 'Lupus glacialis', 'HIGH', 'AGITATED', 'Aggressive when hungry; avoid sudden movement.', 3, NOW()),
    ('Mirelurker Drone', 'Insecta stagnaris', 'MEDIUM', 'STABLE', 'Produces toxic spores when threatened.', 4, NOW()),
    ('Peakstrider Roc', 'Aves montis', 'HIGH', 'OBSERVED', 'Territorial; nests on cliff edges.', 5, NOW()),
    ('Prairie Skipper', 'Lagomorpha ventus', 'LOW', 'STABLE', 'Fast-moving herbivore; harmless.', 6, NOW()),
    ('Brineclaw Scuttler', 'Crustacea salinus', 'MEDIUM', 'STABLE', 'Prefers tidal pools; strong pincers.', 7, NOW()),
    ('Canopy Glider', 'Pteris sylvana', 'LOW', 'STABLE', 'Glides between trees; harmless.', 8, NOW()),
    ('Hollowshade Bat', 'Chiroptera umbra', 'MEDIUM', 'OBSERVED', 'Echolocates aggressively when startled.', 9, NOW()),
    ('Ashfang Salamander', 'Salamandra ignis', 'HIGH', 'QUARANTINED', 'Skin emits embers; keep in heat‑proof enclosure.', 10, NOW()),
    ('Dune Strider', 'Reptilia arenis', 'LOW', 'STABLE', 'Burrows rapidly; avoids confrontation.', 2, NOW()),
    ('Frostbite Hare', 'Lepus glaciei', 'LOW', 'STABLE', 'Thick insulating fur; docile.', 3, NOW()),
    ('Bogwhisper Toad', 'Anura murkensis', 'MEDIUM', 'OBSERVED', 'Produces hallucinogenic croaks.', 4, NOW()),
    ('Shardhorn Ram', 'Ovis fractura', 'MEDIUM', 'STABLE', 'Crystalline horns; charges when provoked.', 5, NOW()),
    ('Suncrest Lark', 'Aves solari', 'LOW', 'STABLE', 'Sings at dawn; harmless.', 6, NOW()),
    ('Tidebreaker Eel', 'Anguilla marina', 'HIGH', 'AGITATED', 'Emits electrical pulses.', 7, NOW()),
    ('Emerald Fang Viper', 'Viperidae viridia', 'HIGH', 'QUARANTINED', 'Highly venomous; handle with extreme caution.', 8, NOW()),
    ('Obsidian Crawler', 'Arthropoda tenebris', 'MEDIUM', 'OBSERVED', 'Prefers darkness; reacts to vibrations.', 9, NOW()),
    ('Lava Plume Drake', 'Draco pyroclast', 'HIGH', 'QUARANTINED', 'Small drake species; heat‑reactive scales.', 10, NOW()),
    ('Gloomtail Lynx', 'Felis umbralis', 'MEDIUM', 'AGITATED', 'Stealthy; becomes aggressive when cornered.', 1, NOW()),
    ('Sandveil Moth', 'Lepidoptera arenosa', 'LOW', 'STABLE', 'Camouflages perfectly in dunes.', 2, NOW()),
    ('Frostbloom Elk', 'Cervid glacialis', 'MEDIUM', 'QUARANTINED', 'Antlers accumulate frost crystals.', 3, NOW()),
    ('Mireback Snapper', 'Testudo stagnaris', 'LOW', 'STABLE', 'Slow-moving; algae grows on shell.', 4, NOW());