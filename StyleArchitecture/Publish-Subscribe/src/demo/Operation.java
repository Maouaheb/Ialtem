package demo;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class Operation extends Event {
	 void subscribe(String channelName, Subscriber subscriber) {
	        if (!channels.containsKey(channelName)) {
	            channels.put(channelName, new ConcurrentHashMap<>());
	        }

	        channels.get(channelName).put(subscriber.hashCode(), new WeakReference<>(subscriber));
	        System.out.println("client with id "+subscriber.id+  "is subscribed in  Event "+channelName);
	    }

	    void publish(String channelName, Post message) {
	        for(Map.Entry<Integer, WeakReference<Object>> subs : channels.get(channelName).entrySet()) {
	            WeakReference<Object> subscriberRef = subs.getValue();

	            Object subscriberObj = subscriberRef.get();

	            for (final Method method : subscriberObj.getClass().getDeclaredMethods()) {
	                Annotation annotation = method.getAnnotation(OnMessage.class);
	                if (annotation != null) {
	                    deliverMessage(subscriberObj, method, message);
	                    System.out.println(message.message.toString());
	                }
	            }
	        }
	    }

	    <T, P extends Post> boolean deliverMessage(T subscriber, Method method, Post message) {
	        try {
	            boolean methodFound = false;
	            for (final Class paramClass : method.getParameterTypes()) {
	                if (paramClass.equals(message.getClass())) {
	                    methodFound = true;
	                    break;
	                }
	            }
	            if (methodFound) {
	                method.setAccessible(true);
	                method.invoke(subscriber, message);
	            }

	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return false;
	    }
}
