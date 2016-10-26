package tel_ran.network.management;

import java.util.HashSet;
import java.util.Set;

import tel_ran.network.management.elements.Router;

public class RoutersMonitor {
	private Set<Router> routers = new HashSet<>();
	private int interval;
	private boolean monitoring;
	
	public void activate() {
		while(monitoring) {
			synchronized (routers) {
				for(Router router : routers) {
					System.out.println(router);
				}
			}
			
			try {
				Thread.sleep(interval*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	
	
	public int getInterval() {
		return interval;
	}



	public void setInterval(int interval) {
		this.interval = interval;
	}



	public boolean isMonitoring() {
		return monitoring;
	}



	public void setMonitoring(boolean monitoring) {
		this.monitoring = monitoring;
	}



	public boolean addRouter(String name, int nInterfaces) {
		synchronized (routers) {
			return routers.add(new Router(name, nInterfaces));
		}
		
	}
	
	public boolean removeRouter(String name) {
		Router router = new Router(name, 0);
		synchronized (routers) {
			return routers.remove(router);
		}
		
	}

}
