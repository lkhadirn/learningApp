CREATE OR REPLACE FUNCTION update_employee_modified_at() RETURNS TRIGGER AS $$
BEGIN
    NEW.modified_at = NOW();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_employee_modified_at
    BEFORE UPDATE ON employee
    FOR EACH ROW
    EXECUTE FUNCTION update_employee_modified_at();
