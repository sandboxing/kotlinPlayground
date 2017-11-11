package rx;

public interface Subscription {
  void unsubscribe();

  boolean isUnsubscribed();
}