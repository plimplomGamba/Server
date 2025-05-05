import { _getPropertyModel as _getPropertyModel_1, ArrayModel as ArrayModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import StatsDTOModel_1 from "./StatsDTOModel.js";
import type StatsWrapper_1 from "./StatsWrapper.js";
class StatsWrapperModel<T extends StatsWrapper_1 = StatsWrapper_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(StatsWrapperModel);
    get statsDTO(): ArrayModel_1<StatsDTOModel_1> {
        return this[_getPropertyModel_1]("statsDTO", (parent, key) => new ArrayModel_1(parent, key, false, (parent, key) => new StatsDTOModel_1(parent, key, false), { meta: { javaType: "java.util.List" } }));
    }
    get totalGoldGambled(): NumberModel_1 {
        return this[_getPropertyModel_1]("totalGoldGambled", (parent, key) => new NumberModel_1(parent, key, false, { meta: { javaType: "java.lang.Long" } }));
    }
}
export default StatsWrapperModel;
