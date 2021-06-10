import React from 'react'
import { Container, Typography } from '@material-ui/core'
import Navigation from '../../../layout/Navigation'
import '../../../layout/Body.css'
import Content from '../contents/Content'

export default function StaffServiceRoot(props) {
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
