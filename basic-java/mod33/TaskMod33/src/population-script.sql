-- Populando marcas
INSERT INTO brand (name, description, country_origin, foundation_year, created_at, updated_at)
VALUES
('Toyota', 'Fabricante japonesa de veículos', 'Japão', 1937, NOW(), NOW()),
('Ford', 'Montadora americana', 'Estados Unidos', 1903, NOW(), NOW()),
('Volkswagen', 'Marca alemã de automóveis', 'Alemanha', 1937, NOW(), NOW());

-- Populando carros
INSERT INTO car (model, manufacturing_year, model_year, color, fueltype, price, created_at, updated_at, brand_id)
VALUES
('Corolla', 2022, 2023, 'Prata', 'GASOLINE', 130000.00, NOW(), NOW(), (SELECT id FROM brand WHERE name = 'Toyota')),
('Mustang', 2021, 2022, 'Vermelho', 'GASOLINE', 280000.00, NOW(), NOW(), (SELECT id FROM brand WHERE name = 'Ford')),
('Golf GTI', 2020, 2021, 'Preto', 'FLEX', 150000.00, NOW(), NOW(), (SELECT id FROM brand WHERE name = 'Volkswagen'));

-- Populando acessórios
INSERT INTO accessory (name, description, price, is_optional, accessory_type, created_at, updated_at, car_id)
VALUES
('Rodas de Liga Leve', 'Rodas aro 17 em alumínio', 3500.00, TRUE, 'EXTERIOR', NOW(), NOW(), (SELECT id FROM car WHERE model = 'Corolla')),
('Bancos de Couro', 'Bancos revestidos em couro sintético', 5000.00, TRUE, 'COMFORT', NOW(), NOW(), (SELECT id FROM car WHERE model = 'Mustang')),
('Sistema de Navegação', 'Central multimídia com GPS integrado', 3000.00, TRUE, 'TECHNOLOGY', NOW(), NOW(), (SELECT id FROM car WHERE model = 'Golf GTI'));
