import { _enum as _enum_1, EnumModel as EnumModel_1, makeEnumEmptyValueCreator as makeEnumEmptyValueCreator_1 } from "@vaadin/hilla-lit-form";
import GameMode_1 from "./GameMode.js";
class GameModeModel extends EnumModel_1<typeof GameMode_1> {
    static override createEmptyValue = makeEnumEmptyValueCreator_1(GameModeModel);
    readonly [_enum_1] = GameMode_1;
}
export default GameModeModel;
