(ns jumski.life-calendar.workspaces.calendar-cards
  (:require [nubank.workspaces.core :as ws]
            [nubank.workspaces.card-types.react :as ct.react]
            [reagent.core :as reagent]))

(defn my-component [& args]
  [:div [:p "om23ghax"]])

(ws/defcard hello-card
  (ct.react/react-card
    (reagent/as-element [my-component])))


