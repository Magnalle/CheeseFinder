# CheeseFinder
With applying MVP pattern to simple android application,  
RxJava/RxAndroid and Dagger 2 are together implemented.

Application simply lists the predefined cheeses as saved as string array in the resource file.

Application has one single activity which includes search box, search button and the list of cheeses as shown below.

![cheeseactivity](https://user-images.githubusercontent.com/10610988/31466156-db516100-aede-11e7-859e-f5d120231c0e.jpeg)

##  RxJava & RxAndroid

In CheeseActivity two observables are created one for search button click and one for search box instant text change.

### Search Button's observale code:


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

# to be continue...
