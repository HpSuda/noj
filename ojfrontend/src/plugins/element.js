import Vue from 'vue'
import { Button,ButtonGroup } from 'element-ui'
import { Form,FormItem} from 'element-ui'
import {Input} from 'element-ui'
import {Message} from 'element-ui'
import {Dialog} from "element-ui"
import {Card} from  'element-ui'
import {Menu,Submenu, MenuItem,MenuItemGroup} from 'element-ui';
import {Container,Header,Main,Footer} from "element-ui";
import {Dropdown,DropdownMenu,DropdownItem} from "element-ui";
import {Divider} from "element-ui";
import {Table,TableColumn} from "element-ui";
import {Pagination} from "element-ui";
import {Tag} from "element-ui"
import {Row,Col} from "element-ui";
import {Upload} from "element-ui"
import {Select,Option} from "element-ui";

Message.install = function (Vue, options) {
     Vue.prototype.$message = Message
 }

Vue.use(Button)
Vue.use(ButtonGroup)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Message)
Vue.use(Dialog)
Vue.use(Card)
Vue.use(Menu)
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(MenuItemGroup)
Vue.use(Container)
Vue.use(Header)
Vue.use(Main)
Vue.use(Footer)
Vue.use(Dropdown)
Vue.use(DropdownMenu)
Vue.use(DropdownItem)
Vue.use(Divider)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Pagination)
Vue.use(Tag)
Vue.use(Row)
Vue.use(Col)
Vue.use(Upload)
Vue.use(Select)
Vue.use(Option)