import path from "path";
import fs from "fs";

// * Config
const formatFileName = (fileName) => {
  return fileName
    .replace(".md", "")
    .split("-")
    .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
    .join(" ");
};

const getMdFiles = (dir) => {
  return fs.readdirSync(dir).flatMap((category) => {
    const categoryPath = path.join(dir, category);

    if (!fs.statSync(categoryPath).isDirectory()) return [];

    return fs
      .readdirSync(categoryPath)
      .filter((file) => file.endsWith(".md"))
      .map((file) => {
        return {
          category,
          text: formatFileName(file),
          link: `/sidebars/${category}/${file}`,
        };
      });
  });
};

const sidebarsDir = path.resolve(__dirname, "../sidebars");

const order = ["theory", "implementation", "evaluation"];

const groupedFiles = getMdFiles(sidebarsDir).reduce(
  (acc, { category, text, link }) => {
    if (!acc[category]) acc[category] = [];
    acc[category].push({ text, link });

    return acc;
  },
  {}
);

let sidebars = Object.entries(groupedFiles).sort(
  ([categoryA], [categoryB]) =>
    order.indexOf(categoryA) - order.indexOf(categoryB)
);

sidebars = sidebars.map(([category, items]) => ({
  text: formatFileName(category),
  items,
}));

export default sidebars;
