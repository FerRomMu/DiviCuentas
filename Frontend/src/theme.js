import { extendTheme } from "@chakra-ui/react";

const theme ={
    config: {
        initialColorMode: "dark",
        useSystemColorMode: true,
    },
    styles: {
        global: {
            boxsizing:'boder-box',
            body: {
                margin: 0,
                "font-family": "'Roboto', sans-serif",
                "-webkit-font-smoothing": "antialiased",
                "-moz-osx-font-smoothing": "grayscale",
            },

            code: {
                "font-family": "source-code-pro, Menlo, Monaco, Consolas, 'Courier New', monospace"
            }
        }
    }
};

export default extendTheme(theme)