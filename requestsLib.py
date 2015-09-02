#!/usr/bin/env python

import exceptions
import requests
import httplib
import urllib2

def request_testing():
	try:
		timeout = 30
		# proxy = 'http://192.168.56.1:8888'
		proxy = ''
		# url = "http://www.bsp.gov.ph/banking/quasi.pdf"
		# url = "http://cca.hawaii.gov/dfi/files/2013/05/Mortgage-Servicer-04.01.14.pdf"
		# url = "http://www.sec.gov.ph/investorinfo/registeredentity/GSEDs.pdf"
		url = "http://acpr.banque-france.fr/fileadmin/user_upload/acp/Agrements_et_autorisations/Procedures_secteur_assurance/Passeport_europeen_assurance/201312-Succursales-europeennes-entreprises-francaises-exercant-en-LPS-dans-EEE.pdf"
		headers = {'User-Agent' : 'Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36'}
		response = requests.get(url, headers=headers, proxies={'http':proxy}, timeout=timeout)
		print "Successfully connected to the website!"
		print "Successful Response Headers: %s" % response.headers
		print "Successful Response Status Code: %s" % response.status_code
		print "Successful Request Headers: %s" % response.request.headers
	except (httplib.BadStatusLine, urllib2.HTTPError, exceptions.NameError, urllib2.URLError) as e:
		print "Error: %s" % e
		print "Could not connect to the website :("
		print "Broken Response Headers: %s" % response.headers
		print "Broken Response Status Code: %s" % response.status_code

if __name__ == '__main__':
	request_testing()
