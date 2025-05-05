import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type DetailedStats_1 from "./DetailedStats.js";
import GameModeModel_1 from "./GameModeModel.js";
class DetailedStatsModel<T extends DetailedStats_1 = DetailedStats_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(DetailedStatsModel);
    get playerName(): StringModel_1 {
        return this[_getPropertyModel_1]("playerName", (parent, key) => new StringModel_1(parent, key, false, { meta: { javaType: "java.lang.String" } }));
    }
    get amount(): NumberModel_1 {
        return this[_getPropertyModel_1]("amount", (parent, key) => new NumberModel_1(parent, key, false, { meta: { javaType: "java.lang.Long" } }));
    }
    get gameMode(): GameModeModel_1 {
        return this[_getPropertyModel_1]("gameMode", (parent, key) => new GameModeModel_1(parent, key, true));
    }
}
export default DetailedStatsModel;
