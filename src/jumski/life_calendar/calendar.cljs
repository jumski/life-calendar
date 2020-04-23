(ns jumski.life-calendar.calendar
  (:require [cljs-time.core :as t]
            [cljs-time.periodic :as p]
            [cljs-time.coerce :as coerce]))

(def before-life-week? t/before?)
(defn life-week? [week birth-date]
  (and (t/after? week birth-date)
       (t/before? week (t/now))))

(defn week->type [week birth-date]
  (cond
    (before-life-week? week birth-date) :blank
    (life-week? week birth-date) :lived
    :else :not-lived))

(defn life-years [birth-date]
  (let [birth-year-date (-> birth-date (t/floor t/year))
        life-years (p/periodic-seq birth-year-date (t/years 1))]
    (take 75 life-years)))

(defn year-weeks [year-date]
  (let [year-no (t/year year-date)
        week-dates (p/periodic-seq year-date (t/weeks 1))]
    (take 52 week-dates)))

(defn life-calendar [birth-date]
  (->> (life-years birth-date)
       (map year-weeks)))

(comment
  (-> (life-calendar (t/date-time 1985 12 25))
      first)
  (-> (life-years (t/date-time 1985 12 25))
      first
      year-weeks
      )

  (->> (life-years (t/date-time 1985 12 25))
       (mapcat year-weeks)
       ; (take 2000)
       (map #(week->type % (t/date-time 1985 12 25))))


  (-> (t/date-time 1985)
      (assoc :year 2000)
      )
  (t/floor (t/date-time 1985 12 25) t/year)

         )
