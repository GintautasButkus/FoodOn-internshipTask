package lt.vtmc.GintautasButkus.models;

public enum EOrderStatus {
	ORDER_CONFIRMED("Confirmed"),
	ORDER_INPROGRESS("Not confirmed");

	 
	private final String label;
	
	private EOrderStatus(final String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	

	
}
