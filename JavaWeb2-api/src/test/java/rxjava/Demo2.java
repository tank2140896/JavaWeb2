package rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class Demo2 {
	
	public static void main(String[] args) {
		Consumer<Long> c1 = new Consumer<Long>() {
			@Override
			public void accept(Long l) throws Exception {
				System.out.println("c1:"+l);
			}
		};
		Consumer<Long> c2 = new Consumer<Long>() {
			@Override
			public void accept(Long l) throws Exception {
				System.out.println(" c2:"+l);
			}
		};
		//Cold Observable
		Observable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
			@Override
			public void subscribe(ObservableEmitter<Long> oe) throws Exception {
				Observable.interval(10,TimeUnit.MILLISECONDS,Schedulers.computation()).take(Integer.MAX_VALUE).subscribe(oe::onNext);
			}
		}).observeOn(Schedulers.newThread());
		observable.subscribe(c1);
		observable.subscribe(c2);
		/**
		//Hot Observable
		ConnectableObservable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
			@Override
			public void subscribe(ObservableEmitter<Long> oe) throws Exception {
				Observable.interval(10,TimeUnit.MILLISECONDS,Schedulers.computation()).take(Integer.MAX_VALUE).subscribe(oe::onNext);
			}
		}).observeOn(Schedulers.newThread()).publish();
		observable.connect();
		observable.subscribe(c1);
		observable.subscribe(c2);
		*/
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
