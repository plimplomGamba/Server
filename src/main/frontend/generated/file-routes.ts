import { createRoute as createRoute_1 } from "@vaadin/hilla-file-router/runtime.js";
import type { AgnosticRoute as AgnosticRoute_1 } from "@vaadin/hilla-file-router/types.js";
import * as Page_1 from "../views/@index.js";
import * as Layout_1 from "../views/@layout.js";
import * as Page_2 from "../views/downloads.js";
import * as Page_3 from "../views/gamba/components/AppAppBar.js";
import * as Page_4 from "../views/gamba/components/Footer.js";
import * as Page_5 from "../views/gamba/components/MainContent.js";
import * as Page_6 from "../views/gamba/Gamba.js";
import * as Page_7 from "../views/gamba/theme/AppTheme.js";
import * as Page_8 from "../views/gamba/theme/ColorModeIconDropdown.js";
import * as Page_9 from "../views/gamba/theme/ColorModeSelect.js";
import * as Page_10 from "../views/stats.js";
const routes: readonly AgnosticRoute_1[] = [
    createRoute_1("", Layout_1, [
        createRoute_1("", Page_1),
        createRoute_1("downloads", Page_2),
        createRoute_1("gamba", [
            createRoute_1("components", [
                createRoute_1("AppAppBar", Page_3),
                createRoute_1("Footer", Page_4),
                createRoute_1("MainContent", Page_5)
            ]),
            createRoute_1("Gamba", Page_6),
            createRoute_1("theme", [
                createRoute_1("AppTheme", Page_7),
                createRoute_1("ColorModeIconDropdown", Page_8),
                createRoute_1("ColorModeSelect", Page_9)
            ])
        ]),
        createRoute_1("stats", Page_10)
    ])
];
export default routes;
