CREATE TRIGGER update_employment_modified_at
    BEFORE UPDATE ON employment
    FOR EACH ROW
    EXECUTE FUNCTION update_modified_at();

