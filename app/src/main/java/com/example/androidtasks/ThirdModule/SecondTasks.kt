package com.example.androidtasks.ThirdModule

class SecondTasks {
    // Сделайте сетевой запрос и отобразите результат на экране? (база)
    api.getUser()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { user ->
                showUser(user)
            },
            { error ->
                showError(error)
            }
        )


    // Сделайте таймер. TextView которая раз в секунду меняется (timer)
    Observable.interval(1, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe{ count ->
            textView.text = count.toString()
        }


    // Сделайте ресайклер. По нажатию на элемент передавайте его позицию во фрагмент. и во фрагменте этот номер отображайте в тосте. (Subject)
    // Adapter
    class Adapter(
        private val clicks: PublishSubject<Int>
    ): RecyclerView.Adapter<Adapter.VH>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return VH(TextView(parent.context))
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.itemView.setOnClickListener {
                clicks.onNext(position)
            }
        }

        override fun getItemCount() = 10

        class VH(view: View): RecyclerView.ViewHolder(view)
    }

    // Fragment
    val clicks = PublishSubject.create<Int>()
    recyclerView.adapter = Adapter(clicks)
    clicks
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe{ position ->
            Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
        }



    // Сделайте EditText. При наборе текста выводите в лог содержимое EditText всегда, когда пользователь 3 секунды что-то не вводил (debounce)
    editText.textChanges()
        .skipInitialValue()
        .debounce(3, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe{ text ->
            Log.d("Text", text.toString())
        }


    // Есть 2 сервера на которых лежат скидочные карты. Нужно получить эти данные и вывести в единый список. (zip и тд)
        //а) Если 1 из запросов падает, то все равно выводить (найти метод RX для такого, чтоб самому не прописывать логику)
        Observable.zip(
            api1.getCards().onErrorReturnItem(emptyList()),
            api2.getCards().onErrorReturnItem(emptyList())
        ) { cards1, cards2 ->
            cards1 + cards2
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe{ cards ->
            showCards(cards)
        }


    //б) Если 1 из запросов падает, то не выводить ничего (найти метод RX)
    Observable.zip(
        api1.getCards(),
        api2.getCards()
    ) { cards1, cards2 ->
        cards1 + cards2
    }
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(
        { cards -> showCards(cards) },
        { error -> showError(error) }
    )
}
