(ns jumski.life-calendar.workspaces.calendar-cards
  (:require [nubank.workspaces.core :as ws]
            [nubank.workspaces.card-types.react :as ct.react]
            [reagent.core :as reagent]
            [cljs.test :refer-macros [is testing]]))

(defn my-component [& args]
  [:div [:p "om3ghax"]])

(ws/defcard hello-card
  (ct.react/react-card
    (reagent/as-element [my-component])))

(ws/deftest sample-test
  (is (= 1 2)))
