import { _getPropertyModel as _getPropertyModel_1, ArrayModel as ArrayModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import DetailedStatsModel_1 from "./DetailedStatsModel.js";
import type StatsDTO_1 from "./StatsDTO.js";
class StatsDTOModel<T extends StatsDTO_1 = StatsDTO_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(StatsDTOModel);
    get playerName(): StringModel_1 {
        return this[_getPropertyModel_1]("playerName", (parent, key) => new StringModel_1(parent, key, false, { meta: { javaType: "java.lang.String" } }));
    }
    get detailedStats(): ArrayModel_1<DetailedStatsModel_1> {
        return this[_getPropertyModel_1]("detailedStats", (parent, key) => new ArrayModel_1(parent, key, false, (parent, key) => new DetailedStatsModel_1(parent, key, false), { meta: { javaType: "java.util.List" } }));
    }
    get fullAmount(): NumberModel_1 {
        return this[_getPropertyModel_1]("fullAmount", (parent, key) => new NumberModel_1(parent, key, false, { meta: { javaType: "java.lang.Long" } }));
    }
}
export default StatsDTOModel;
