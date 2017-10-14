# CheeseFinder
With applying MVP pattern to simple android application,  
RxJava/RxAndroid and Dagger 2 are together implemented.

Application simply lists the predefined cheeses as saved as string array in the resource file.

Application has one single activity which includes search box, search button and the list of cheeses as shown below.

![cheeseactivity](https://user-images.githubusercontent.com/10610988/31466156-db516100-aede-11e7-859e-f5d120231c0e.jpeg)

##  RxJava & RxAndroid

In CheeseActivity two observables are created one for search button click and one for search box instant text change.

### Search Button's observale code:

It observes the seach button click and send to emitter the search text at that time.

```
private Observable<String> createButtonClickObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                mSearchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emitter.onNext(mQueryEditText.getText().toString());
                    }
                });

                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        mSearchButton.setOnClickListener(null);
                    }
                });
            }
        });
```

### Search Box's observable code:

It observes the seach text instant changes and send to emitter the search text at that time.

```
private Observable<String> createTextChangeObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> e) throws Exception {
                final TextWatcher watcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        e.onNext(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                };

                mQueryEditText.addTextChangedListener(watcher);

                e.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        mQueryEditText.removeTextChangedListener(watcher);
                    }
                });

            }
        });
    }
```

### Using of observables

#### Method onserveOn()

Decides to android threads. Doing view based work we have to choose main thread with using below code:

```
.observeOn(AndroidSchedulers.mainThread())
```
But for searching the text from cheese list to find matched cheeses we may use asysnc thread with using below code:

```
.observeOn(Schedulers.io())
```

#### Method map()

- Returns an Observable that applies a specified function to each item emitted by the source ObservableSource and emits the results of these function applications. 

We are using the "map()" method here to search the matched cheeses.

```
.map(new Function<String, List<String>>() {
        @Override
        public List<String> apply(String s) throws Exception {
                return mCheesePresenter.search(s);
        }
})
```

#### Method filter()
- Filters items emitted by an ObservableSource by only emitting those that satisfy a specified predicate.

We are using the "filter()" method here to limit the minimun text length is needed to do search while typing in to search box.

```
.filter(new Predicate<String>() {
        @Override
        public boolean test(String s) throws Exception {
                return s.length() >= 2;
        }
})
```

#### Method Debounce()

- Returns an Observable that mirrors the source ObservableSource, except that it drops items emitted by the source ObservableSource that are followed by newer items before a timeout value expires. The timer resets on each emission.

We are using the "debounce()" method here to postphone the seacrhing after for each character typing because waiting the users' full search text will be more efective.

```
.debounce(DEBOUNCE_TIME_MS, TimeUnit.MILLISECONDS)
```

#### Method skipWhile()

- Returns an Observable that skips all items emitted by the source ObservableSource as long as a specified condition holds true, but emits all further source items as soon as the condition becomes false.

We are using the "skipWhile()" method to skip previous search if the search text has change while waiting the user to stop typing. As you see below:

```
.skipWhile(new Predicate<String>() {
    @Override
    public boolean test(String s) throws Exception {
        return !mQueryEditText.getText().toString().equals(s);
    }
})
