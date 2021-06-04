import React from 'react'
import Content from '../contents/content';
import { Container, Typography } from '@material-ui/core'
import Navigation from '../../../layout/Navigation'
import '../../../layout/Body.css'

export default function Staffreceptionroot(props) {
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
