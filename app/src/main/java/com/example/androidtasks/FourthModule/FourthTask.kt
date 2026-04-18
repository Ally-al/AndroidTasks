package com.example.androidtasks.FourthModule

// 1. Функциональные требования
//      отображение списка чатов
//      отправка сообщений
//      получение новых сообщений


// 2. Нефункциональные требования
//      работа при отсутствии интернета



// 3. Фреймворки и библиотеки

//      MVVM
//          ViewModel хранит состояние экранов (чаты и сообщения)
//          UI только отображает данные и вызывает методы ViewModel

//      Kotlin Coroutines + Flow
//          асинхронная работа с сетью и базой данных
//          обновление UI при изменении данных

//      Room
//          локальное хранилище чатов и сообщений
//          используется как кэш и источник данных для UI
//          позволяет отображать данные без интернета

//      Retrofit
//          HTTP запросы к серверу
//          получение чатов и сообщений
//          отправка сообщений

//      WebSocket
//          постоянное соединение с сервером
//          получение новых сообщений в реальном времени



// 4. Компоненты системы по слоям

//      Presentation
//          ChatListFragment
//          ChatFragment
//          ChatListViewModel
//          ChatViewModel

//      Domain слой, модели:
//          Chat
//          Message

//      Data
//          Repository
//          Room
//          Retrofit API
//          WebSocketClient



// 5. Работа системы

//      Открытие списка чатов

//          создается ChatListFragment
//          в ChatListFragment вызывается chatListViewModel.loadChats()
//          ChatListViewModel подписывается на Flow<List<Chat>> из Repository (repository.getChats())
//          Repository отдаёт Flow<List<Chat>> из Room
//          параллельно ChatListViewModel запускает repository.syncChats()

//          syncChats():
//              вызывает api.getChats()
//              получает список чатов с сервера
//              сохраняет их в Room

//          Room обновляет данные в базе
//          Flow из Room автоматически эмитит новый список
//          ChatListViewModel получает обновления через Flow и обновляет StateFlow
//          ChatListFragment подписан на StateFlow из ChatListViewModel и отображает список чатов в RecyclerView



//      Отправка сообщения

//          при открытии экрана:
//              ChatViewModel подписывается на Flow<List<Message>> из Repository
//              ChatFragment подписывается на StateFlow из ChatViewModel и отображает сообщения в RecyclerView

//          пользователь вводит текст и нажимает кнопку отправки
//          в ChatFragment вызывается chatViewModel.sendMessage(text)
//              создается объект Message
//              вызывается repository.sendMessage(message)

//          Repository:
//              сохраняет сообщение в Room
//              отправляет сообщение на сервер через api.sendMessage(message)

//          Room обновляет данные в базе
//          Flow из Room автоматически эмитит новый список сообщений
//          ChatViewModel получает обновления через Flow и обновляет StateFlow
//          ChatFragment отображает обновленный список сообщений в RecyclerView



//      Получение сообщения

//          приложение поддерживает WebSocket соединение

//          сервер отправляет новое сообщение через WebSocket
//          приложение получает сообщение в WebSocket listener в Repository

//          Repository:
//              сохраняет сообщение в Room

//          Room обновляет данные в базе
//          Flow из Room автоматически эмитит обновления
//          ChatViewModel и ChatListViewModel получают обновления через Flow и обновляют StateFlow
//          ChatFragment и ChatListFragment автоматически обновляют RecyclerView
