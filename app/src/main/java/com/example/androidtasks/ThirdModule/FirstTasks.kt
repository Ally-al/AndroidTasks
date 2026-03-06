package com.example.androidtasks.ThirdModule

class FirstTasks {
    Observable.timer(10, TimeUnit.MILLISECONDS, Schedulers.newThread())
    .subscribeOn(Schedulers.io())
    .map {
        Log.d("HAHAHA", "mapThread = ${Thread.currentThread().name}")
    }
    .doOnSubscribe {
        Log.d("HAHAHA", "onSubscribeThread = ${Thread.currentThread().name}")
    }
    .subscribeOn(Schedulers.computation())
    .observeOn(Schedulers.single())
    .flatMap {
        Log.d("HAHAHA", "flatMapThread = ${Thread.currentThread().name}")
        Observable.just(it)
            .subscribeOn(Schedulers.io())
    }
    .subscribe {
        Log.d("HAHAHA", "subscribeThread = ${Thread.currentThread().name}")
    }

    // В логе:
    // "HAHAHA", "onSubscribeThread = Schedulers.computation"
    // "HAHAHA", "mapThread = Schedulers.newThread"
    // "HAHAHA", "flatMapThread = Schedulers.single"
    // "HAHAHA", "subscribeThread = Schedulers.single"

    val subject = PublishSubject.create<String>()
    subject.onNext("1")
    subject.onNext("2")
    subject.onNext("3")
    subject.subscribe { Log.d("TAG", it) }

    // В логе:
    // Ничего не будет, так как используется PublishSubject и подписка происходит уже после эммитов
    // Чтобы все вывелось надо использовать ReplaySubject вместо PublishSubject
    // Или перенести подписку выше всех onNext
}
