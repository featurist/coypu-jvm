


package Coypu.Drivers.Tests
{
    class When_finding_fields_by_id_ends_with extends DriverSpecs
    {
        @Test
        public void Finds_by_id_ends_with()
        {
            Driver().FindField("tainerLabeledFileFieldId", Root()).Name, is(equalTo("containerLabeledFileFieldName");
        }

        @Test
        public void Finds_by_complete_id_before_finding_by_id_ends_with()
        {
            Driver().FindField("checkedBox",Root()).Id(), is(equalTo("checkedBox");
        }
    }
}