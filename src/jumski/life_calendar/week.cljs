(ns jumski.life-calendar.week
  (:require [cljs-time.core :as t]))

(defrecord Week [start])

(defn date->Week [date]
  (let [wday (t/weekday date)
        delta (- wday 1)
        start (t/minus date (t/days delta))]
    (Week. start)))
