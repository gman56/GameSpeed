package siteFiles;

public class AutoCompleteData {
	
	
	private final String label;
    private final Object value;

    public AutoCompleteData(String _label, Object _value) {
        super();
        this.label = _label;
        this.value = _value;
    }

    public final String getLabel() {
        return this.label;
    }

    public final Object getValue() {
        return this.value;
    }
	
	

}
