(ns jumski.life-calendar.calendar
  (:require [cljs-time.core :as t]
            [cljs-time.periodic :as p]
            [cljs-time.predicates :as predicates]
            [cljs-time.coerce :as coerce]))

(defn- life-years [birth-date]
  (let [life-years (p/periodic-seq birth-date (t/years 1))]
    (take 75 life-years)))

(defn- year-weeks [year-date]
  (let [year-no (t/year year-date)
        week-dates (p/periodic-seq year-date (t/weeks 1))]
    (take 52 week-dates)))

(defn same-week? [date1 date2]
  (and (= (t/year date1) (t/year date2))
       (= (t/week-number-of-year date1) (t/week-number-of-year date2))))

(defn week-before? [date1 date2]
  (and (t/before? date1 date2)
       (not (= (t/week-number-of-year date1) (t/week-number-of-year date2)))))

(defn create-for-date [birth-date]
  (->> (life-years birth-date)
       (map year-weeks)))
