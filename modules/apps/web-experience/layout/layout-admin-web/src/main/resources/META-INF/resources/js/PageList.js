import Component from "metal-component";
import { Config } from "metal-state";
import Soy from "metal-soy";

import "./PageListBreadcrumbs";
import "./PageListColumn";
import templates from "./PageList.soy";

/**
 * Component that allows to show layouts tree in form of columns.
 * Each column represents a depth level of navigation.
 */
class PageList extends Component {
  /**
   * @inheritDoc
   */
  attached() {
    const A = new AUI();

    A.use("liferay-search-container", "liferay-search-container-select", A => {
      const plugins = [];

      plugins.push({
        cfg: {
          rowSelector: ".list-group-item"
        },
        fn: A.Plugin.SearchContainerSelect
      });

      const searchContainer = new Liferay.SearchContainer({
        contentBox: A.one(this.refs.pageList),
        id:
          this.getInitialConfig().portletNamespace +
          this.getInitialConfig().searchContainerId,
        plugins: plugins
      });

      this.searchContainer_ = searchContainer;

      Liferay.fire("search-container:registered", {
        searchContainer: searchContainer
      });
    });
  }

  /**
   * @inheritDoc
   */
  rendered() {
    this.refs.pageListColumns.scrollLeft = this.refs.pageListColumns.scrollWidth;
  }
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
PageList.STATE = {
  /**
   * Layout blocks
   * @default undefined
   * @instance
   * @memberof PageList
   * @type {!Array}
   */
  layoutBlocks: Config.arrayOf(
    Config.arrayOf(
      Config.shapeOf({
        active: Config.bool().required(),
        hasChild: Config.bool().required(),
        icon: Config.string().required(),
        layoutId: Config.string().required(),
        parentLayoutId: Config.string().required(),
        selected: Config.bool().required(),
        title: Config.string().required()
      })
    )
  ).required(),

  /**
   * URL for using icons
   * @default undefined
   * @instance
   * @memberof PageList
   * @type {!string}
   */
  pathThemeImages: Config.string().required(),

  /**
   * Namespace of portlet to prefix parameters names
   * @default undefined
   * @instance
   * @memberof PageList
   * @type {!string}
   */
  portletNamespace: Config.string().required(),

  /**
   * URL of portlet to prefix block links
   * @default undefined
   * @instance
   * @memberof PageList
   * @type {!string}
   */
  portletURL: Config.string().required()
};

Soy.register(PageList, templates);

export default PageList;
