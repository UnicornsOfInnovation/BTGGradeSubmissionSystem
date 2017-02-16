/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Rococo Global Technologies, Inc - All Rights Reserved 2016
 * --------------------------------------------------------------------------- */
 
/*
* This file contains the backend process for the web page.
* The holds the values/properties that is common to the application.
* @author Lehmar Cabrillos
 * @version 0.01
 * Version History
 * [04/13/2016] 0.01 �ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ Lehmar Cabrillos  �ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ Initial codes.
 */


/**
 * The angular module object.
 * @param pizzaTimeApp - the application name (refer to the 'ng-app' directive)
 */
app.controller('bestStudentController', function($scope, $http, $httpParamSerializer) {
	console.log("bestStudentController " + "start");
	
	$scope.courseID = 973;
	$scope.courseDetails = null;
	$scope.teacherList = [];
	$scope.studentList = [];
	$scope.courseList= [];
	$scope.gradeList = [];
	$scope.newGradeList=[];
	$scope.newCourseList=[];
	$scope.newCourseCodeList=[];
	$scope.bestStudentList = [];
	$scope.courseModel = null;
	$scope.myTableArray = [];
	$scope.teacher = {
			firstName: "",
			lastName: "",
	}	

	var columns = ['Course','Student Name'];

	$scope.callme = function (){
		html2canvas(document.getElementById("class-list-primary"),{
			onrendered: function (canvas){
				for(var ctr = 0; ctr<$scope.bestStudentList.length;ctr++){
					    var arrayOfThisRow = [];
					    var studentName = $scope.bestStudentList[ctr].firstName + " " +$scope.bestStudentList[ctr].lastName;
					    arrayOfThisRow.push($scope.bestStudentList[ctr].courseName);
					    arrayOfThisRow.push(studentName);    
					    
					    $scope.myTableArray.push(arrayOfThisRow);
				}
				var columns2 = [" "," "];
				var array2=[];
				array2.push(["Best Student Table"," "]);

				
				
			    var doc = new jsPDF('p', 'pt');
			    doc.autoTable(columns2, array2, {margin:{top:80}, theme: 'plain'});
			    var header = function(data) {
			        doc.setFontSize(18);
			        doc.setTextColor(40);
			        doc.setFontStyle('normal');
			        //doc.addImage(headerImgData, 'JPEG', data.settings.margin.left, 20, 50, 50);
			        doc.text("Best Student List", data.settings.margin.left, 50);
			      };
		        var options = {
		    		    beforePageContent: header,
		    		    margin: {
		    		      top: 160
		    		    },
		    		    startY: doc.autoTableEndPosY() + 20
		    		  };
		        doc.autoTable(columns, $scope.myTableArray, options);

			    doc.save("Best-Student-List.pdf");
			}
		})
	}
	
	
	
$scope.getBestStudentList = function(){
		
		var object = {
				action: "GetAllBestStudents"  //flag to determine which controller to use
		}
		$http.post("/BestStudents",  $httpParamSerializer(object),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			if (response.data.errorList.length == 0) {
				
				$scope.bestStudentList = response.data.bestStudentList;
			} else {
				var errorMessage = "";
				for (var i = 0; i < response.data.errorList.length; i++) {
					errorMessage += response.data.errorList[i];
				}
				alert(errorMessage);
			}
		}, function() {
			alert("An error has occured");
		});
	}
	
	$scope.listCourses = function() {
		console.log("courseController.listCourses " + "start");
		var course = {
				action: "GetAllCourses" //flag to determine which controller to use
		}
		$http.post("/course",  $httpParamSerializer(course),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			if (response.data.errorList.length == 0) {
				$scope.courseList = response.data.courseDtoList;
				$scope.courseModel = $scope.courseList[0];
				
			} else {
				var errorMessage = "";
				for (var i = 0; i < response.data.errorList.length; i++) {
					errorMessage += response.data.errorList[i];
				}
				alert(errorMessage);
			}
		}, function() {
			alert("An error has occured");
		});
		console.log("courseController.listCourses " + "end");
	}
	
		
	$scope.listCourses();
	$scope.getBestStudentList();
});