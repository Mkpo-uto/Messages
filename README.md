### Synchronization, wait/notify/notifyall methods

1. To get around the deadlock condition is where the wait, notify and notifyall
methods come into play. When a thread calls the wait() method it suspends execution
and releases whatever lock it is holding until another thread issues a notification 
that something important has happened. The other thread issues the notification by 
calling the notify() or notifyall() methods
