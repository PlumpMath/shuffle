# shuffle

A Clojure library designed to ... well, shuffle.

## Usage

```clojure
;use this to find the number of shuffles needed to sort the deck
(shuf-sort cut-size initial-shuffles deck)
;use this to shuffle the deck
(shuf cut-size deck)

;use shuf-stats to get the number of shuffles needed to reorder
;the deck
(shuf-stats cut size)
```

The naive implementation is done..
Next up is expermenting/adding a way to get the cycles out of a list
## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
