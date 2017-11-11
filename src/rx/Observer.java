package rx;

public interface Observer<T> {
  void onNext(T t);

  void onError(Throwable throwable);

  void onCompleted();
}
