SELECT column_name, data_type
FROM information_schema.columns
WHERE table_name = 'gasto' AND column_name = 'nombre';
