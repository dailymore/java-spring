import { defineConfig } from "vitepress";
import formattedSidebars from "../sidebars";

export default defineConfig({
  title: "Spring Docs",
  description: "Java Spring training",
  outDir: "../../dist",
  srcDir: "src",
  vite: { server: { port: 2000 } },
  themeConfig: {
    nav: [
      { text: "Home", link: "/" },
      // { text: "Guide", link: "/markdown-examples" },
    ],
    sidebar: formattedSidebars,
    socialLinks: [
      { icon: "github", link: "https://github.com/cmhehe176/training-spring" },
    ],
  },
});
