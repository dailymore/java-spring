import { defineConfig } from "vitepress";
import sidebars from "../sidebars";

export default defineConfig({
  title: "Spring Docs",
  description: "Java Spring training",
  outDir: "../dist",
  base: "/java-spring/",
  vite: { server: { port: 2000 } },
  themeConfig: {
    nav: [
      { text: "Home", link: "/" },
      // { text: "Guide", link: "/markdown-examples" },
    ],
    sidebar: sidebars,
    socialLinks: [
      { icon: "github", link: "https://github.com/cmhehe176/training-spring" },
    ],
  },
});
