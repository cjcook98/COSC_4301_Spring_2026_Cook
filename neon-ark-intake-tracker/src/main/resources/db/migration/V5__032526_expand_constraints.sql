-- Drop old biome constraint
ALTER TABLE habitats
DROP CONSTRAINT IF EXISTS chk_habitats_biome;

-- Add updated biome constraint
ALTER TABLE habitats
    ADD CONSTRAINT chk_habitats_biome
        CHECK (biome IN (
                         'FOREST',
                         'DESERT',
                         'TUNDRA',
                         'SWAMP',
                         'MOUNTAIN',
                         'GRASSLAND',
                         'COASTAL',
                         'RAINFOREST',
                         'CAVE',
                         'VOLCANIC'
            ));

-- Drop old danger_level constraint (this one is fine, but keeping pattern consistent)
ALTER TABLE creatures
DROP CONSTRAINT IF EXISTS chk_creatures_danger_level;

ALTER TABLE creatures
    ADD CONSTRAINT chk_creatures_danger_level
        CHECK (danger_level IN ('LOW', 'MEDIUM', 'HIGH'));

-- Drop old condition constraint
ALTER TABLE creatures
DROP CONSTRAINT IF EXISTS chk_creatures_condition;

-- Add updated condition constraint
ALTER TABLE creatures
    ADD CONSTRAINT chk_creatures_condition
        CHECK (condition IN ('STABLE', 'QUARANTINED', 'CRITICAL', 'AGITATED', 'OBSERVED'));
