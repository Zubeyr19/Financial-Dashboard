import React from 'react'
import styled from 'styled-components'
import { ThemeProvider } from 'styled-components';

function Button({name, icon, onClick, bg, bPad, color, bRad}) {
    return (
        <ButtonStyled style={{
            background: bg,
            padding: bPad,
            borderRadius: bRad,
            color: color,
        }} onClick={onClick}>
            {icon}
            {name}
        </ButtonStyled>
    )
}

const ButtonStyled = styled.button`
    outline: none;
    border: none;
    font-family: inherit;
    font-size: inherit;
    display: flex;
    align-items: center;
    gap: 0.5rem;
    cursor: pointer;
    transition: all 0.4s ease-in-out;
    background: ${({ theme }) => theme.buttonBackground || '#007bff'};
    color: ${({ theme }) => theme.buttonColor || '#fff'};
    padding: ${({ theme }) => theme.buttonPadding || '10px 20px'};
    border-radius: ${({ theme }) => theme.buttonBorderRadius || '4px'};

    &:hover {
        opacity: 0.8;
    }
`;
const theme = {
    buttonBackground: '#007bff',
    buttonColor: '#fff',
    buttonPadding: '10px 20px',
    buttonBorderRadius: '4px',
};

function App() {
    return (
        <ThemeProvider theme={theme}>
            <Button onClick={() => console.log('Clicked!')}>Click Me</Button>
        </ThemeProvider>
    );
}

export default Button