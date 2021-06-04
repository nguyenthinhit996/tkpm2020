
import React, { useEffect } from 'react'
import {Container,Typography } from '@material-ui/core'
import Navigation from '../../../layout/Navigation'
import '../../../layout/Body.css'
import Content from "../contents/content"

export default function Managerstaffroot(props) {
    return (
        <>
            <Container>
                <Typography component="div" className="containerQuanliKhachSan">
                    <Navigation />
                    <Content />
                </Typography>
            </Container>
        </>
    )
}
