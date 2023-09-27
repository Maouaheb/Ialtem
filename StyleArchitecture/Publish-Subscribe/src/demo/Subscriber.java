package demo;

public class Subscriber {
	  int id;
	    public Subscriber(int id) {
	        this.id = id;
	    }

	    @OnMessage
	    private void onMessage(Message message) {
	    	//System.out.println("Subcriber id"+id+"  is created");
	    }
}
