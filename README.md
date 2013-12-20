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
Shuf-stats works by running shuffle a the number of times there are
cards in the deck to build up a list of all of the cycles that occur
when you shuffle the deck. Then it gets the least common multiple of
the lengths of all of the cycles, this is the number of times you
would have to shuffle the deck to get it back to it's original state.

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
