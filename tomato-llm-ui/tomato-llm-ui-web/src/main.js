import { createApp } from 'vue'

// 全局完整注册 ant-design-vue 组件 start
import Antd, { message } from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import * as antIcons from '@ant-design/icons-vue';
// 全局完整注册 ant-design-vue 组件 end

// 自定义样式 start
import './theme/index.less';
// 自定义样式 end


import App from './App.vue'

function initVue() {
    let vueApp = createApp(App);
    let app = vueApp.use(Antd);
    //挂载
    app.mount('#app');
}

initVue();