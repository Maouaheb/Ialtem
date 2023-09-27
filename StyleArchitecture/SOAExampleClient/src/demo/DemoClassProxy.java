package demo;

public class DemoClassProxy implements demo.DemoClass {
  private String _endpoint = null;
  private demo.DemoClass demoClass = null;
  
  public DemoClassProxy() {
    _initDemoClassProxy();
  }
  
  public DemoClassProxy(String endpoint) {
    _endpoint = endpoint;
    _initDemoClassProxy();
  }
  
  private void _initDemoClassProxy() {
    try {
      demoClass = (new demo.DemoClassServiceLocator()).getDemoClass();
      if (demoClass != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)demoClass)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)demoClass)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (demoClass != null)
      ((javax.xml.rpc.Stub)demoClass)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public demo.DemoClass getDemoClass() {
    if (demoClass == null)
      _initDemoClassProxy();
    return demoClass;
  }
  
  public int calculSomme(int a, int b) throws java.rmi.RemoteException{
    if (demoClass == null)
      _initDemoClassProxy();
    return demoClass.calculSomme(a, b);
  }
  
  
}