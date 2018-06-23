package rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Demo1 {
	
	/**
	 * Observable   Observer	                       能够发射0或n个数据，并以成功或错误事件终止
	 * Flowable	    Subscriber	                       能够发射0或n个数据，并以成功或错误事件终止。支持背压，可以控制数据源发射的速度
     * Single	    SingleObserver       只发射单个数据或错误事件
     * Completable	CompletableObserver  从来不发射数据，只处理onComplete和onError事件。可以看成Rx的Runnable
     * Maybe	    MaybeObserver        能够发射0或者1个数据，要么成功，要么失败。有点类似于Optional
	 */
	public static void main(String[] args) {
		Observable.just("Hello World").subscribe(System.out::println);

		Observable.just("Hello World").subscribe(new Observer<String>(){
			@Override
			public void onSubscribe(Disposable disposable) {
				System.out.println(1);
			}
			@Override
			public void onNext(String str) {
				System.out.println(2);
			}
			@Override
			public void onComplete() {
				System.out.println(3);
			}
			@Override
			public void onError(Throwable throwable) {
				System.out.println(4);
			}
		});

		Observable.just("Hello World")
				  .doOnNext(new Consumer<String>() {
					  @Override
					  public void accept(@NonNull String str) throws Exception {
						  System.out.println("A："+str);
					  }
				  })
				  .subscribe(new Consumer<String>() {
					  @Override
					  public void accept(@NonNull String str) throws Exception {
						  System.out.println("B："+str);
					  }
				  });
	}

}
