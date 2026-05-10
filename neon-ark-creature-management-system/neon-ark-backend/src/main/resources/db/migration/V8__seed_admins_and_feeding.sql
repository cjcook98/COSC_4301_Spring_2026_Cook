-- Seed Users
INSERT INTO users (full_name, email, phone, role) VALUES
    ('Astra Venn', 'astra.venn@neonark.org', '555-1010', 'ADMIN'),
    ('Rylan Shore', 'rylan.shore@neonark.org', '555-2020', 'STAFF'),
    ('Mira Solari', 'mira.solari@neonark.org', '555-3030', 'STAFF');

-- Seed Feeding Schedules
INSERT INTO feeding_schedules (creature_id, feed_time) VALUES
    (1, '08:00'),
    (2, '08:00'),
    (3, '12:00'),
    (4, '12:00'),
    (5, '18:00'),
    (6, '18:00'),
    (7, '18:00'),
    (10, '20:30'),
    (12, '20:30'),
    (15, '06:15');